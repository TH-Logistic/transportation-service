package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.CreateTransportationRequest;
import com.thlogistic.transportation.adapters.dtos.CreateTransportationResponse;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransportationUseCaseImpl implements CreateTransportationUseCase {

    private final TransportationRepository repository;
    private final GetGarageUseCase getGarageUseCase;

    @Override
    public CreateTransportationResponse execute(CreateTransportationRequest request) {
        // Check if garage is existed
        getGarageUseCase.execute(request.getGarageId());

        TransportationEntity entity = new TransportationEntity(
                request.getLicensePlate(),
                request.getLoad(),
                request.getGarageId(),
                DeliveryStatus.IDLE,
                request.getMainDriverId(),
                request.getCoDriverId());
        String id = repository.insert(entity);
        return new CreateTransportationResponse(id);
    }
}
