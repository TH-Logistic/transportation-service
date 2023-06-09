package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.UpdateTransportationRequest;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.core.entities.Transportation;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateTransportationUseCaseImpl implements UpdateTransportationUseCase {

    private final TransportationRepository repository;

    @Override
    public Object execute(Pair<String, UpdateTransportationRequest> requestPair) {
        // Check delivery status in request
        UpdateTransportationRequest request = requestPair.getSecond();

        Optional<TransportationEntity> entity = repository.findById(requestPair.getFirst());
        if (entity.isEmpty()) {
            throw new DataNotFoundException(Transportation.class.getSimpleName());
        }
        TransportationEntity result = entity.get();
        result.setLicensePlate(request.getLicensePlate());
        result.setLoad(request.getLoad());
        repository.save(result);
        return null;
    }
}
