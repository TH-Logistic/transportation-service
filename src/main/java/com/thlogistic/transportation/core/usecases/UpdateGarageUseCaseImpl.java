package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.UpdateGarageRequest;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateGarageUseCaseImpl implements UpdateGarageUseCase {

    private final GarageRepository repository;

    @Override
    public Object execute(Pair<String, UpdateGarageRequest> requestPair) {
        Optional<GarageEntity> entity = repository.findById(requestPair.getFirst());
        if (entity.isEmpty()) {
            throw new DataNotFoundException(Garage.class.getSimpleName());
        }
        UpdateGarageRequest request = requestPair.getSecond();
        GarageEntity result = entity.get();
        result.setName(request.getName());
        result.setAddress(request.getAddress());
        result.setLatitude(request.getLatitude());
        result.setLongitude(request.getLongitude());
        repository.save(result);
        return null;
    }
}
