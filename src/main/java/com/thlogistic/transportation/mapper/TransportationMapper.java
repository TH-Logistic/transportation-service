package com.thlogistic.transportation.mapper;

import com.thlogistic.transportation.adapters.dtos.GetDriverInfoDto;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.entities.Transportation;

public class TransportationMapper {
    public static GetTransportationResponse toGetTransportationResponse(Transportation transportation, Garage garage) {
        GetTransportationResponse response = new GetTransportationResponse();
        response.setId(transportation.getId());
        response.setLoad(transportation.getLoad());
        response.setDeliveryStatus(transportation.getDeliveryStatus().status);
        response.setLicensePlate(transportation.getLicensePlate());
        if (garage != null) {
            response.setGarage(GarageMapper.fromGarageToResponse(garage));
        }

        // TODO: Add Driver info
        GetDriverInfoDto mainDriverInfoDto = GetDriverInfoDto.builder()
                .id("TODO")
                .avatarUrl("TODO")
                .name("TODO")
                .phoneNumber("TODO")
                .dateOfBirth("TODO")
                .build();

        GetDriverInfoDto coDriverInfoDto = GetDriverInfoDto.builder()
                .id("TODO")
                .avatarUrl("TODO")
                .name("TODO")
                .phoneNumber("TODO")
                .dateOfBirth("TODO")
                .build();
        return response;
    }
}
