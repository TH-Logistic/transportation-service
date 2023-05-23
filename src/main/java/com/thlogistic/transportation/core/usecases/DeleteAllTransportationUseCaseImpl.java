package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.client.user.UserClient;
import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.entities.Transportation;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import com.thlogistic.transportation.mapper.TransportationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteAllTransportationUseCaseImpl implements DeleteAllTransportationUseCase {
    private final TransportationRepository transportationRepository;

    @Override
    public Boolean execute(Boolean aBoolean) {
        transportationRepository.deleteAll();
        return true;
    }
}
