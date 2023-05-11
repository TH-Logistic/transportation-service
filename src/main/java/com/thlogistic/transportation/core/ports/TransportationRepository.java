package com.thlogistic.transportation.core.ports;

import com.thlogistic.transportation.adapters.repositories.BasePagingQueryResult;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.entities.TransportationEntity;

import java.util.List;
import java.util.Optional;

public interface TransportationRepository {
    String insert(TransportationEntity transportation);

    String save(TransportationEntity transportation);

    Optional<TransportationEntity> findById(String id);
    Optional<TransportationEntity> findByDriverId(String mainDriverId);

    BasePagingQueryResult<List<TransportationEntity>> list(String keyword, DeliveryStatus deliveryStatus, Integer page, Integer size);

    BasePagingQueryResult<List<TransportationEntity>> listWithoutDeliveryStatus(String keyword, Integer page, Integer size);
}
