package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.entities.TransportationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTransportationRepository extends MongoRepository<TransportationEntity, String> {
    Page<TransportationEntity> findByLicensePlateContainingIgnoreCaseAndDeliveryStatusIs(String keyword, DeliveryStatus deliveryStatus, Pageable pageable);

    Page<TransportationEntity> findByLicensePlateContainingIgnoreCase(String keyword, Pageable pageable);
}
