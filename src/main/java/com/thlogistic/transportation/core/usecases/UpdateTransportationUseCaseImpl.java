package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.UpdateTransportationRequest;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Transportation;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateTransportationUseCaseImpl implements UpdateTransportationUseCase {

    private final TransportationRepository repository;
    private final GetGarageUseCase getGarageUseCase;

    @Override
    public Object execute(Pair<String, UpdateTransportationRequest> requestPair) {
        // Check delivery status in request
        UpdateTransportationRequest request = requestPair.getSecond();
        if (Objects.equals(request.getDeliveryStatus(), DeliveryStatus.IDLE.status)) {
            if (request.getGarageId() == null || request.getGarageId().isEmpty()) {
                throw new RuntimeException("Require garage if delivery status is Idle");
            }
        }
        // Check if garage id in request exists
        getGarageUseCase.execute(request.getGarageId());

        Optional<TransportationEntity> entity = repository.findById(requestPair.getFirst());
        if (entity.isEmpty()) {
            throw new DataNotFoundException(Transportation.class.getSimpleName());
        }
        TransportationEntity result = entity.get();
        result.setLicensePlate(request.getLicensePlate());
        result.setLoad(request.getLoad());
        result.setDeliveryStatus(DeliveryStatus.fromInt(request.getDeliveryStatus()));
        if (Objects.equals(request.getDeliveryStatus(), DeliveryStatus.DELIVERY.status)) {
            result.setGarageId(null);
        } else {
            result.setGarageId(request.getGarageId());
        }
        result.setMainDriverId(request.getMainDriverId());
        result.setCoDriverId(request.getCoDriverId());
        repository.save(result);
        return null;
    }
}
