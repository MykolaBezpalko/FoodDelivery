package com.fooddelivery.service.impl;

import com.fooddelivery.model.Establishment;
import com.fooddelivery.model.Status;
import com.fooddelivery.repository.EstablishmentRepository;
import com.fooddelivery.service.EstablishmentService;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    @Resource
    private EstablishmentRepository establishmentRepository;

    @Override
    public List<Establishment> getAll() {
        return Streamable.of(establishmentRepository.findAll()).toList();
    }

    @Override
    public Establishment create(Establishment establishment) {
        Optional<Establishment> establishmentOptional =
                establishmentRepository.findByEstablishmentName(establishment.getEstablishmentName());

        if (establishmentOptional.isPresent()) {
            establishment.setEstablishmentId(establishmentOptional.get().getEstablishmentId());
            return this.update(establishment);
        }

        establishment.setCreateDate(LocalDateTime.now());
        establishment.setUpdateDate(LocalDateTime.now());
        establishment.setStatus(Status.ACTIVE);
        return establishmentRepository.save(establishment);
    }

    @Override
    public Establishment getById(Long id) {
        return establishmentRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Establishment> establishmentOptional = establishmentRepository.findById(id);
        if (establishmentOptional.isPresent()) {
            Establishment establishment = establishmentOptional.get();
            establishment.setStatus(Status.DELETED);
            establishment.setUpdateDate(LocalDateTime.now());
            establishmentRepository.save(establishment);
        }
    }

    @Override
    public Establishment update(Establishment establishment) {
        establishment.setUpdateDate(LocalDateTime.now());
        return establishmentRepository.save(establishment);
    }
}
