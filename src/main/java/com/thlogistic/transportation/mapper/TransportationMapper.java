package com.thlogistic.transportation.mapper;

import com.thlogistic.transportation.adapters.dtos.GetDriverInfoDto;
import com.thlogistic.transportation.adapters.dtos.GetTransportationNoDriverInfoResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Transportation;

public class TransportationMapper {
    public static GetTransportationResponse toGetTransportationResponse(Transportation transportation) {
        GetTransportationResponse.GetTransportationResponseBuilder responseBuilder = GetTransportationResponse.builder();
        responseBuilder.id(transportation.getId());
        responseBuilder.load(transportation.getLoad());
        responseBuilder.deliveryStatus(transportation.getDeliveryStatus().status);
        responseBuilder.licensePlate(transportation.getLicensePlate());
        responseBuilder.isInGarage(transportation.getDeliveryStatus() == DeliveryStatus.IDLE);

        // TODO: Get Driver info
        GetDriverInfoDto mainDriverInfoDto = GetDriverInfoDto.builder()
                .id(transportation.getMainDriverId())
                .avatarUrl("TODO")
                .name("TODO")
                .phoneNumber("TODO")
                .dateOfBirth("TODO")
                .build();

        GetDriverInfoDto coDriverInfoDto = GetDriverInfoDto.builder()
                .id(transportation.getCoDriverId())
                .avatarUrl("TODO")
                .name("TODO")
                .phoneNumber("TODO")
                .dateOfBirth("TODO")
                .build();

        responseBuilder.mainDriverInfo(mainDriverInfoDto);
        responseBuilder.coDriverInfo(coDriverInfoDto);

        return responseBuilder.build();
    }

    public static GetTransportationNoDriverInfoResponse toGetTransportationNoDriverInfoResponse(Transportation transportation) {
        GetTransportationNoDriverInfoResponse response = new GetTransportationNoDriverInfoResponse();
        response.setId(transportation.getId());
        response.setLoad(transportation.getLoad());
        response.setDeliveryStatus(transportation.getDeliveryStatus().status);
        response.setLicensePlate(transportation.getLicensePlate());
        response.setIsInGarage(transportation.getDeliveryStatus() == DeliveryStatus.IDLE);

        return response;
    }
}
