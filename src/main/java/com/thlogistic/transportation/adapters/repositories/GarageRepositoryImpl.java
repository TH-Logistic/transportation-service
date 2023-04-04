package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public BasePagingQueryResult<List<GarageEntity>> list(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GarageEntity> garages;
        if (keyword == null || keyword.isEmpty()) {
            garages = repository.findAll(pageable);
        } else {
            garages = repository.findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword, pageable);
        }

        BasePagingQueryResult<List<GarageEntity>> result = new BasePagingQueryResult<>();
        result.data = garages.getContent();
        result.total = garages.getTotalElements();
        result.totalPage = garages.getTotalPages();
        return result;
    }
}
