package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public Optional<TransportationEntity> findByDriverId(String driverId) {
        return repository.findByMainDriverIdOrCoDriverId(driverId, driverId);
    }

    @Override
    public Optional<TransportationEntity> findByLicensePlate(String licensePlate) {
        return repository.findByLicensePlate(licensePlate);
    }

    @Override
    public BasePagingQueryResult<List<TransportationEntity>> list(String keyword,
                                                                  DeliveryStatus deliveryStatus,
                                                                  Integer page,
                                                                  Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TransportationEntity> entities;
        if (keyword == null || keyword.isEmpty()) {
            entities = repository.findAll(pageable);
        } else {
            entities = repository.findByLicensePlateContainingIgnoreCaseAndDeliveryStatusIs(keyword, deliveryStatus, pageable);
        }

        BasePagingQueryResult<List<TransportationEntity>> result = new BasePagingQueryResult<>();
        result.data = entities.getContent();
        result.total = entities.getTotalElements();
        result.totalPage = entities.getTotalPages();
        return result;
    }

    @Override
    public BasePagingQueryResult<List<TransportationEntity>> listWithoutDeliveryStatus(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TransportationEntity> entities;
        if (keyword == null || keyword.isEmpty()) {
            entities = repository.findAll(pageable);
        } else {
            entities = repository.findByLicensePlateContainingIgnoreCase(keyword, pageable);
        }

        BasePagingQueryResult<List<TransportationEntity>> result = new BasePagingQueryResult<>();
        result.data = entities.getContent();
        result.total = entities.getTotalElements();
        result.totalPage = entities.getTotalPages();
        return result;
    }
}
