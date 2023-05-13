package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationNoDriverInfoResponse;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Transportation;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import com.thlogistic.transportation.entities.TransportationEntity;
import com.thlogistic.transportation.mapper.TransportationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTransportationByDriverIdUseCaseImpl implements GetTransportationByDriverIdUseCase {

    private final TransportationRepository transportationRepository;
    private final GarageRepository garageRepository;

    @Override
    public GetTransportationNoDriverInfoResponse execute(BaseTokenRequest<String> baseTokenRequest) {
        Optional<TransportationEntity> entityOptional = transportationRepository.findByDriverId(baseTokenRequest.getRequestContent());
        if (entityOptional.isEmpty()) {
            throw new DataNotFoundException(Transportation.class.getSimpleName());
        }
        TransportationEntity entity = entityOptional.get();

        if (entity.getDeliveryStatus() == DeliveryStatus.IDLE) {
            GarageEntity garageEntity = garageRepository.findById(entity.getGarageId()).get();
            return TransportationMapper.toGetTransportationNoDriverInfoResponse(
                    entity.toTransportation(),
                    garageEntity.toGarage()
            );
        }

        return TransportationMapper.toGetTransportationNoDriverInfoResponse(
                entity.toTransportation(),
                null
        );
    }
}
