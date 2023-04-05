package com.thlogistic.transportation.mapper;

import com.thlogistic.transportation.adapters.dtos.GetGarageResponse;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.entities.GarageEntity;

public class GarageMapper {
    public static GetGarageResponse fromGarageEntityToResponse(GarageEntity entity) {
        return new GetGarageResponse(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getLatitude(),
                entity.getLongitude()
        );
    }

    public static GetGarageResponse fromGarageToResponse(Garage garage) {
        return new GetGarageResponse(
                garage.getId(),
                garage.getName(),
                garage.getAddress(),
                garage.getLatitude(),
                garage.getLongitude()
        );
    }
}
