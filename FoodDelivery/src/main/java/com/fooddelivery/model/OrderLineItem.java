package com.fooddelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "OrderLineItem")
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId")
    @JsonIgnore
    @ToString.Exclude
    private Order order;

    @NotNull
    private String userEmail;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "dish_count_mapping",
            joinColumns = {@JoinColumn(name = "orderLineItemId", referencedColumnName = "orderLineItemId")})
    @MapKeyJoinColumn(name = "dishId")
    @Column(name = "count")
    @EqualsAndHashCode.Exclude
    private Map<Dish, Integer> dishCountMap = new HashMap<>();

    @Builder.Default
    @NotNull
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @NotNull
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status = Status.ACTIVE;

}
