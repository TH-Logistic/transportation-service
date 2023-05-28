package com.thlogistic.transportation.mapper;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.entities.Transportation;

public class TransportationMapper {
    public static GetTransportationResponse toGetTransportationResponse(
            Transportation transportation,
            Garage garage,
            UserInfoDto mainDriver,
            UserInfoDto coDriver
    ) {
        GetTransportationResponse.GetTransportationResponseBuilder responseBuilder = GetTransportationResponse.builder();
        responseBuilder.id(transportation.getId());
        responseBuilder.load(transportation.getLoad());
        responseBuilder.deliveryStatus(transportation.getDeliveryStatus().status);
        responseBuilder.licensePlate(transportation.getLicensePlate());
        responseBuilder.isInGarage(transportation.getDeliveryStatus() == DeliveryStatus.IDLE);

        if (garage != null) {
            GetGarageResponse garageResponse = GarageMapper.fromGarageToResponse(garage);
            responseBuilder.garage(garageResponse);
        }

        GetDriverInfoDto mainDriverInfoDto = GetDriverInfoDto.builder()
                .id(transportation.getMainDriverId())
                .avatarUrl(mainDriver.getAvatar())
                .name(mainDriver.getName())
                .gender(mainDriver.getGender())
                .phoneNumber(mainDriver.getPhoneNumber())
                .dateOfBirth(mainDriver.getBirthday())
                .build();

        GetDriverInfoDto coDriverInfoDto = GetDriverInfoDto.builder()
                .id(transportation.getCoDriverId())
                .avatarUrl(coDriver.getAvatar())
                .name(coDriver.getName())
                .gender(mainDriver.getGender())
                .phoneNumber(coDriver.getPhoneNumber())
                .dateOfBirth(coDriver.getBirthday())
                .build();

        responseBuilder.mainDriver(mainDriverInfoDto);
        responseBuilder.coDriver(coDriverInfoDto);

        return responseBuilder.build();
    }

    public static GetTransportationWithDriverNameResponse toGetTransportationWithDriverNameResponse(
            Transportation transportation,
            Garage garage,
            UserInfoDto mainDriver,
            UserInfoDto coDriver
    ) {
        GetTransportationWithDriverNameResponse response = new GetTransportationWithDriverNameResponse();
        response.setId(transportation.getId());
        response.setLoad(transportation.getLoad());
        response.setDeliveryStatus(transportation.getDeliveryStatus().status);
        response.setLicensePlate(transportation.getLicensePlate());
        response.setIsInGarage(transportation.getDeliveryStatus() == DeliveryStatus.IDLE);
        response.setMainDriver(mainDriver.getName());
        response.setCoDriver(coDriver.getName());

        if (garage != null) {
            GetGarageResponse garageResponse = GarageMapper.fromGarageToResponse(garage);
            response.setGarage(garageResponse);
        }

        return response;
    }

    public static GetTransportationNoDriverInfoResponse toGetTransportationNoDriverInfoResponse(
            Transportation transportation,
            Garage garage
    ) {
        GetTransportationNoDriverInfoResponse response = new GetTransportationNoDriverInfoResponse();
        response.setId(transportation.getId());
        response.setLoad(transportation.getLoad());
        response.setDeliveryStatus(transportation.getDeliveryStatus().status);
        response.setLicensePlate(transportation.getLicensePlate());
        response.setIsInGarage(transportation.getDeliveryStatus() == DeliveryStatus.IDLE);

        if (garage != null) {
            GetGarageResponse garageResponse = GarageMapper.fromGarageToResponse(garage);
            response.setGarage(garageResponse);
        }

        return response;
    }
}
