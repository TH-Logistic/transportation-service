package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GarageRepositoryImpl implements GarageRepository {
    private final MongoGarageRepository repository;

    @Override
    public String insert(GarageEntity garage) {
        return repository.insert(garage).getId();
    }

    @Override
    public String save(GarageEntity garage) {
        return repository.save(garage).getId();
    }

    @Override
    public Optional<GarageEntity> findById(String id) {
        return repository.findById(id);
    }
}
