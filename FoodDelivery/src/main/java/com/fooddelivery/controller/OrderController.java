package com.fooddelivery.controller;

import com.fooddelivery.dto.request.OrderRequestDTO;
import com.fooddelivery.dto.response.OrderResponseDTO;
import com.fooddelivery.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping
    @Parameter(hidden = true)
    public OrderResponseDTO create(@Valid @RequestBody OrderRequestDTO orderRequestDTO, @AuthenticationPrincipal OidcUser principal) {
        return orderService.create(orderRequestDTO, principal.getEmail());
    }

    @PutMapping("/{id}")
    public OrderResponseDTO update(@Valid @RequestBody OrderRequestDTO orderRequestDTO, @PathVariable("id") Long id) {
        return orderService.update(orderRequestDTO, id);
    }

    @GetMapping()
    public List<OrderResponseDTO> getByLimitOffset(@NotNull Integer limit, @NotNull Integer offset) {
        return orderService.getByLimitOffset(limit, offset);
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getById(@PathVariable("id") @NotNull Long id) {
        return orderService.getById(id);
    }

}
