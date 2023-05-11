package com.thlogistic.transportation.mapper;

import com.thlogistic.transportation.adapters.dtos.GetDriverInfoDto;
import com.thlogistic.transportation.adapters.dtos.GetTransportationIdAndLicensePlateResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
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

    public static GetTransportationIdAndLicensePlateResponse toGetTransportationIdAndLicensePlateResponse(Transportation transportation) {
        GetTransportationIdAndLicensePlateResponse response = new GetTransportationIdAndLicensePlateResponse();
        response.setId(transportation.getId());
        response.setLicensePlate(transportation.getLicensePlate());

        return response;
    }
}
