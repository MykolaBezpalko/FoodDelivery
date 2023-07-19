package com.fooddelivery.service;

import com.fooddelivery.dto.request.OrderLineItemRequestDTO;
import com.fooddelivery.dto.response.OrderLineItemResponseDTO;

import javax.validation.constraints.NotNull;

public interface OrderLineItemService {

    OrderLineItemResponseDTO getById(@NotNull Long id);

    OrderLineItemResponseDTO create(@NotNull OrderLineItemRequestDTO orderLineItemRequest);

    OrderLineItemResponseDTO update(@NotNull OrderLineItemRequestDTO orderLineItemRequest);

    void deleteById(@NotNull Long id);

}
