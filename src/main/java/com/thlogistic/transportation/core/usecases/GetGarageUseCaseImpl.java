package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetGarageUseCaseImpl implements GetGarageUseCase {

    private final GarageRepository garageRepository;

    @Override
    public Garage execute(String id) {
        Optional<GarageEntity> entity = garageRepository.findById(id);
        if (entity.isEmpty()) {
            throw new DataNotFoundException(Garage.class.getSimpleName());
        }
        return entity.get().toGarage();
    }
}
