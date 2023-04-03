package com.thlogistic.transportation.core.ports;

import com.thlogistic.transportation.entities.TransportationEntity;

import java.util.Optional;

public interface TransportationRepository {
    String insert(TransportationEntity transportation);

    String save(TransportationEntity transportation);

    Optional<TransportationEntity> findById(String id);

}
