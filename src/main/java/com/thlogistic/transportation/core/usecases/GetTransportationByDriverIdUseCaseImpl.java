package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.aop.exception.CustomRuntimeException;
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
public class GetTransportationByDriverIdUseCaseImpl implements GetTransportationByDriverIdUseCase {

    private final TransportationRepository transportationRepository;
    private final GetGarageUseCase getGarageUseCase;
    private final UserClient userClient;

    @Override
    public GetTransportationResponse execute(BaseTokenRequest<String> baseTokenRequest) {
        String token = baseTokenRequest.getToken();
        Optional<TransportationEntity> entityOptional = transportationRepository.findByDriverId(baseTokenRequest.getRequestContent());
        if (entityOptional.isEmpty()) {
            throw new DataNotFoundException(Transportation.class.getSimpleName());
        }
        TransportationEntity entity = entityOptional.get();

        String mainDriverId = entity.getMainDriverId();
        String coDriverId = entity.getCoDriverId();

        UserInfoDto mainDriverDto;
        UserInfoDto coDriverDto;

        try {
            mainDriverDto = userClient.getUser(token, mainDriverId).getData();
            coDriverDto = userClient.getUser(token, coDriverId).getData();
        } catch (Exception e) {
            throw new CustomRuntimeException("An error occurred when get driver info");
        }

        if (entity.getDeliveryStatus() == DeliveryStatus.IDLE) {
            Garage garage = getGarageUseCase.execute(entity.getGarageId());
            return TransportationMapper.toGetTransportationResponse(
                    entity.toTransportation(),
                    garage,
                    mainDriverDto,
                    coDriverDto
            );
        }

        return TransportationMapper.toGetTransportationResponse(
                entity.toTransportation(),
                null,
                mainDriverDto,
                coDriverDto
        );
    }
}
