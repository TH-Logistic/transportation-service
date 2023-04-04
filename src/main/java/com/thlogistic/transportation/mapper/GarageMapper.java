package com.thlogistic.transportation.mapper;

import com.thlogistic.transportation.adapters.dtos.GetGarageResponse;
import com.thlogistic.transportation.entities.GarageEntity;

public class GarageMapper {
    public static GetGarageResponse fromGarageEntityToResponse(GarageEntity entity) {
        GetGarageResponse response = new GetGarageResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setAddress(entity.getAddress());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        return response;
    }
}
