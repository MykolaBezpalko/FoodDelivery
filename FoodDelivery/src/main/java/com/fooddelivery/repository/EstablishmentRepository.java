package com.fooddelivery.repository;

import com.fooddelivery.model.Establishment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {

    Optional<Establishment> findByEstablishmentName(String establishmentName);

}
