package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.client.user.UserClient;
import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import com.thlogistic.transportation.entities.TransportationEntity;
import com.thlogistic.transportation.mapper.TransportationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetGarageDetailUseCaseImpl implements GetGarageDetailUseCase {
    private final GetGarageUseCase getGarageUseCase;
    private final TransportationRepository transportationRepository;
    private final UserClient userClient;

    @Override
    public GetGarageDetailResponse execute(BaseTokenRequest<String> baseTokenRequest) {
        String token = baseTokenRequest.getToken();
        String garageId = baseTokenRequest.getRequestContent();

        Garage garage = getGarageUseCase.execute(garageId);

        List<TransportationEntity> transportationEntities = transportationRepository.listByGarageId(garageId);

        List<GetTransportationNoGarageResponse> transportations = transportationEntities.stream().map(entity -> {
            String mainDriverId = entity.getMainDriverId();
            String coDriverId = entity.getCoDriverId();

            UserInfoDto mainDriverDto;
            UserInfoDto coDriverDto;

            try {
                mainDriverDto = userClient.getUser(token, mainDriverId).getData();
                coDriverDto = userClient.getUser(token, coDriverId).getData();
            } catch (Exception e) {
                throw new RuntimeException("An error occurred when get driver info");
            }

            return new GetTransportationNoGarageResponse(
                    entity.getId(),
                    entity.getLicensePlate(),
                    entity.getLoad(),
                    mainDriverDto.getName(),
                    coDriverDto.getName()
            );
        }).toList();

        return new GetGarageDetailResponse(
                garage,
                null,
                transportations
        );
    }
}
