package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.entities.GarageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoGarageRepository extends MongoRepository<GarageEntity, String> {
    Page<GarageEntity> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address, Pageable pageable);
    List<GarageEntity> findAllByNameContainingIgnoreCase(String name);
}
