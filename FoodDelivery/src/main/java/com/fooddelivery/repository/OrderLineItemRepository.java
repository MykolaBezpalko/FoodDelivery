package com.fooddelivery.repository;

import com.fooddelivery.model.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {

    OrderLineItem findByOrderOrderId(Long orderId);
}
