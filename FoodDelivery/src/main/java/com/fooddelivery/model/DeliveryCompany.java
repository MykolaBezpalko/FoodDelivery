package com.fooddelivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "DeliveryCompany")
public class DeliveryCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryCompanyId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status = Status.ACTIVE;

    @NotBlank
    private String deliveryCompanyName;

    private String imageLink;

    @Builder.Default
    @NotNull
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @NotNull
    private LocalDateTime updateDate = LocalDateTime.now();

}
