package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.core.entities.Transportation;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import com.thlogistic.transportation.mapper.TransportationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTransportationUseCaseImpl implements GetTransportationUseCase {

    private final TransportationRepository transportationRepository;

    @Override
    public GetTransportationResponse execute(String id) {
        Optional<TransportationEntity> entityOptional = transportationRepository.findById(id);
        if (entityOptional.isEmpty()) {
            throw new DataNotFoundException(Transportation.class.getSimpleName());
        }
        TransportationEntity entity = entityOptional.get();

        return TransportationMapper.toGetTransportationResponse(
                entity.toTransportation()
        );
    }
}
