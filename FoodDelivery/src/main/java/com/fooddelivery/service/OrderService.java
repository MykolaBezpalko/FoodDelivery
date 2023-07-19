package com.fooddelivery.service;

import com.fooddelivery.dto.request.OrderRequestDTO;
import com.fooddelivery.dto.response.OrderResponseDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderService {

    List<OrderResponseDTO> getAll();

    List<OrderResponseDTO> getByLimitOffset(@NotNull Integer limit,@NotNull Integer offset);

    OrderResponseDTO getById(@NotNull Long id);

    OrderResponseDTO create(@NotNull OrderRequestDTO orderRequest, @NotNull String userEmail);

    OrderResponseDTO update(@NotNull OrderRequestDTO orderRequest, @NotNull Long id);

    void deleteById(@NotNull Long id);

}
