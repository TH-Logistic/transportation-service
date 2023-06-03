package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.entities.TransportationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoTransportationRepository extends MongoRepository<TransportationEntity, String> {
    Optional<TransportationEntity> findByMainDriverIdOrCoDriverId(String mainDriverId, String coDriverId);

    Optional<TransportationEntity> findByLicensePlate(String licensePlate);

    Page<TransportationEntity> findByLicensePlateContainingIgnoreCaseAndDeliveryStatusIs(String keyword, DeliveryStatus deliveryStatus, Pageable pageable);

    Page<TransportationEntity> findByLicensePlateContainingIgnoreCase(String keyword, Pageable pageable);

    List<TransportationEntity> findByGarageId(String garageId);

    List<TransportationEntity> findAllByLicensePlateContainingIgnoreCaseOrGarageIdIsIn(String licensePlate, List<String> garageIds);
    List<TransportationEntity> findAllByGarageIdIsIn(List<String> garageIds);
}
