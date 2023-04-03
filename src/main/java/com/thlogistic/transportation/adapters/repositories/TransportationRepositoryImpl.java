package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransportationRepositoryImpl implements TransportationRepository {
    private final MongoTransportationRepository repository;

    @Override
    public String insert(TransportationEntity transportation) {
        return repository.insert(transportation).getId();
    }

    @Override
    public String save(TransportationEntity transportation) {
        return repository.save(transportation).getId();
    }

    @Override
    public Optional<TransportationEntity> findById(String id) {
        return repository.findById(id);
    }
}
