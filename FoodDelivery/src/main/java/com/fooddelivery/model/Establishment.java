package com.fooddelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Establishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long establishmentId;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String establishmentName;

    @Builder.Default
    @NotBlank
    private String establishmentLocation = "Ivano-Frankivsk";

    @Min(0)
    private Double deliveryCost;

    @Builder.Default
    @Min(0)
    @Max(100)
    private Integer ratePercent = 0;

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

    private String imageLink;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "establishment", fetch = FetchType.LAZY)
    private Set<Dish> dishes = new HashSet<>();
}
