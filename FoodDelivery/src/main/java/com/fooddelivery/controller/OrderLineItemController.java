package com.fooddelivery.controller;

import com.fooddelivery.dto.request.OrderLineItemRequestDTO;
import com.fooddelivery.dto.response.OrderLineItemResponseDTO;
import com.fooddelivery.service.OrderLineItemService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/order-line-items")
public class OrderLineItemController {

    @Resource
    private OrderLineItemService orderLineItemService;

    @PostMapping
    public OrderLineItemResponseDTO create(@Valid @RequestBody OrderLineItemRequestDTO orderLineItemRequestDTO) {
        return orderLineItemService.create(orderLineItemRequestDTO);
    }

    @PutMapping
    public OrderLineItemResponseDTO update(@Valid @RequestBody OrderLineItemRequestDTO orderLineItemRequestDTO) {
        return orderLineItemService.update(orderLineItemRequestDTO);
    }

}
