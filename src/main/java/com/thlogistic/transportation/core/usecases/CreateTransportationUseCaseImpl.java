package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.CreateTransportationRequest;
import com.thlogistic.transportation.adapters.dtos.CreateTransportationResponse;
import com.thlogistic.transportation.aop.exception.BadRequestException;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.client.user.UserClient;
import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateTransportationUseCaseImpl implements CreateTransportationUseCase {

    private final TransportationRepository repository;
    private final GetGarageUseCase getGarageUseCase;
    private final UserClient userClient;

    @Override
    public CreateTransportationResponse execute(BaseTokenRequest<CreateTransportationRequest> baseTokenRequest) {
        String token = baseTokenRequest.getToken();
        CreateTransportationRequest requestContent = baseTokenRequest.getRequestContent();
        // Check if garage is existed
        getGarageUseCase.execute(requestContent.getGarageId());

        // Check if driver is already in charge of another transportation
        Optional<TransportationEntity> mainDriverEntityOptional = repository.findByDriverId(requestContent.getMainDriverId());
        if (mainDriverEntityOptional.isPresent()) {
            throw new BadRequestException("Main driver is already in charge of another transportation");
        }
        Optional<TransportationEntity> coDriverEntityOptional = repository.findByDriverId(requestContent.getCoDriverId());
        if (coDriverEntityOptional.isPresent()) {
            throw new BadRequestException("Co-driver is already in charge of another transportation");
        }

        // Check if user exists
        try {
            BaseResponse<UserInfoDto> mainDriverResponse = userClient.getUser(token, requestContent.getMainDriverId());
            BaseResponse<UserInfoDto> coDriverResponse = userClient.getUser(token, requestContent.getCoDriverId());
            if (!mainDriverResponse.getSuccess() || !coDriverResponse.getSuccess()) {
                throw new DataNotFoundException("User");
            }
        } catch (Exception e) {
            throw new DataNotFoundException("User");
        }

        // Check if license plate exists
        Optional<TransportationEntity> licensePlateEntity = repository.findByLicensePlate(requestContent.getLicensePlate());
        if (licensePlateEntity.isPresent()) {
            throw new BadRequestException("License plate already exists");
        }

        TransportationEntity entity = new TransportationEntity(
                requestContent.getLicensePlate(),
                requestContent.getLoad(),
                requestContent.getGarageId(),
                DeliveryStatus.IDLE,
                requestContent.getMainDriverId(),
                requestContent.getCoDriverId());
        String id = repository.insert(entity);
        return new CreateTransportationResponse(id);
    }
}
