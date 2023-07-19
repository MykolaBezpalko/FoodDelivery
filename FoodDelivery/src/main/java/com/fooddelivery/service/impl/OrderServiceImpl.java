package com.fooddelivery.service.impl;

import com.fooddelivery.dto.request.OrderRequestDTO;
import com.fooddelivery.dto.response.OrderResponseDTO;
import com.fooddelivery.model.Establishment;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderStatus;
import com.fooddelivery.model.Status;
import com.fooddelivery.repository.OrderRepository;
import com.fooddelivery.service.DeliveryCompanyService;
import com.fooddelivery.service.EstablishmentService;
import com.fooddelivery.service.OrderService;
import com.fooddelivery.utils.OffsetPageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private EstablishmentService establishmentService;

    @Resource
    private DeliveryCompanyService deliveryCompanyService;

    @Override
    public List<OrderResponseDTO> getAll() {
        return orderRepository.findAll().stream().map(OrderResponseDTO::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDTO> getByLimitOffset(@NotNull Integer limit, @NotNull Integer offset) {
        return orderRepository.findAll(OffsetPageable.of(limit, offset))
                .get()
                .map(OrderResponseDTO::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getById(@NotNull Long id) {
        return OrderResponseDTO.convertFromEntity(orderRepository.findById(id).get());
    }

    @Override
    public OrderResponseDTO create(@NotNull OrderRequestDTO orderRequest, @NotNull String userEmail) {
        LocalDateTime currentTime = LocalDateTime.now();
        Establishment establishment = establishmentService.getById(orderRequest.getEstablishmentId());
        Order order = Order.builder()
                .userEmail(userEmail)
                .createDate(currentTime)
                .updateDate(currentTime)
                .endOfOrder(orderRequest.getEndOfOrder())
                .orderName(orderRequest.getOrderName())
                .deliveryCompany(deliveryCompanyService.getById(orderRequest.getDeliveryCompanyId()))
                .establishment(establishment)
                .deliveryCost(establishment.getDeliveryCost())
                .orderStatus(orderRequest.getOrderStatus())
                .build();
        return OrderResponseDTO.convertFromEntity(orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO update(@NotNull OrderRequestDTO orderRequest, @NotNull Long id) {
        Order order = orderRepository.getById(id);
        order.setEndOfOrder(orderRequest.getEndOfOrder());
        order.setOrderName(orderRequest.getOrderName());
        order.setDeliveryCompany(deliveryCompanyService.getById(orderRequest.getDeliveryCompanyId()));
        order.setEstablishment(establishmentService.getById(orderRequest.getEstablishmentId()));
        order.setDeliveryCost(order.getEstablishment().getDeliveryCost());
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setUpdateDate(LocalDateTime.now());
        return OrderResponseDTO.convertFromEntity(orderRepository.save(order));
    }

    @Override
    public void deleteById(@NotNull Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order oldOrder = orderOptional.get();
            oldOrder.setOrderStatus(OrderStatus.DELETED);
            oldOrder.setUpdateDate(LocalDateTime.now());
            orderRepository.save(oldOrder);
        }
    }
}
