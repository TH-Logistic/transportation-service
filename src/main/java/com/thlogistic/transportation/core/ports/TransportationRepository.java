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
    Optional<TransportationEntity> findByLicensePlate(String licensePlate);

    BasePagingQueryResult<List<TransportationEntity>> paging(String keyword, DeliveryStatus deliveryStatus, Integer page, Integer size);

    BasePagingQueryResult<List<TransportationEntity>> pagingWithoutDeliveryStatus(String keyword, Integer page, Integer size);
    List<TransportationEntity> listByGarageId(String garageId);
    void deleteAll();
}
