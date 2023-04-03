package com.thlogistic.transportation.core.ports;

import com.thlogistic.transportation.entities.GarageEntity;

import java.util.Optional;

public interface GarageRepository {
    String insert(GarageEntity garage);

    String save(GarageEntity garage);

    Optional<GarageEntity> findById(String id);

}
