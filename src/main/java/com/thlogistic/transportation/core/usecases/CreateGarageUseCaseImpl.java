package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.CreateGarageRequest;
import com.thlogistic.transportation.adapters.dtos.CreateGarageResponse;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateGarageUseCaseImpl implements CreateGarageUseCase {

    private final GarageRepository repository;

    @Override
    public CreateGarageResponse execute(CreateGarageRequest request) {
        GarageEntity entity = new GarageEntity(
                request.getName(),
                request.getAddress(),
                request.getLatitude(),
                request.getLongitude());
        String id = repository.insert(entity);
        return new CreateGarageResponse(id);
    }
}
