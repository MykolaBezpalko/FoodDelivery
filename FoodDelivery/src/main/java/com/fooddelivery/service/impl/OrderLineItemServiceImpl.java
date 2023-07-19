package com.fooddelivery.service.impl;

import com.fooddelivery.dto.request.OrderLineItemRequestDTO;
import com.fooddelivery.dto.response.OrderLineItemResponseDTO;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderLineItem;
import com.fooddelivery.model.Status;
import com.fooddelivery.repository.OrderLineItemRepository;
import com.fooddelivery.repository.OrderRepository;
import com.fooddelivery.service.DishService;
import com.fooddelivery.service.OrderLineItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderLineItemServiceImpl implements OrderLineItemService {

    @Resource
    private OrderLineItemRepository orderLineItemRepository;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private DishService dishService;

    @Override
    public OrderLineItemResponseDTO getById(@NotNull Long id) {
        return OrderLineItemResponseDTO.convertFromEntity(orderLineItemRepository.findById(id).get());
    }

    @Override
    public OrderLineItemResponseDTO create(@NotNull OrderLineItemRequestDTO orderLineItemRequest) {
        LocalDateTime currentTime = LocalDateTime.now();
        Order order = orderRepository.getById(orderLineItemRequest.getOrderLineItemId());
        OrderLineItem orderLineItem = OrderLineItem.builder()
                .userEmail(order.getUserEmail())
                .order(order)
                .createDate(currentTime)
                .updateDate(currentTime)
                .status(Status.ACTIVE)
                .dishCountMap(
                        orderLineItemRequest.getDishesIdCountMap().entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        k -> dishService.getById(k.getKey()),
                                        Map.Entry::getValue)
                                )
                )
                .build();
        return OrderLineItemResponseDTO.convertFromEntity(orderLineItemRepository.save(orderLineItem));
    }

    @Override
    public OrderLineItemResponseDTO update(@NotNull OrderLineItemRequestDTO orderLineItemRequest) {
        OrderLineItem orderLineItem = orderLineItemRepository.findByOrderOrderId(orderLineItemRequest.getOrderLineItemId());
        orderLineItem.setDishCountMap(
                orderLineItemRequest.getDishesIdCountMap().entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                k -> dishService.getById(k.getKey()),
                                Map.Entry::getValue)
                        )
        );
        orderLineItem.setUpdateDate(LocalDateTime.now());
        return OrderLineItemResponseDTO.convertFromEntity(orderLineItemRepository.save(orderLineItem));
    }

    @Override
    public void deleteById(@NotNull Long id) {
        Optional<OrderLineItem> orderLineItemOptional = orderLineItemRepository.findById(id);
        if (orderLineItemOptional.isPresent()) {
            OrderLineItem oldOrderLineItem = orderLineItemOptional.get();
            oldOrderLineItem.setStatus(Status.DELETED);
            oldOrderLineItem.setUpdateDate(LocalDateTime.now());
            orderLineItemRepository.save(oldOrderLineItem);
        }
    }
}
