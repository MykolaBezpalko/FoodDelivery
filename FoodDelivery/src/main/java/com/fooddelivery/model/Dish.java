package com.fooddelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    @NotNull
    @NotBlank
    private String dishName;

    @NotNull
    @Min(0)
    private Double dishCost;

    @Lob
    private String description;

    private String imageLink;

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

    @ManyToOne
    @JoinColumn(name = "EstablishmentId")
    @JsonIgnore
    private Establishment establishment;
}
