package com.thlogistic.transportation.adapters.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTransportationResponse {
    String id;
    String licensePlate;
    Double load;
    Integer deliveryStatus;
    Boolean isInGarage;
    GetGarageResponse garage;
    GetDriverInfoDto mainDriverInfo;
    GetDriverInfoDto coDriverInfo;
}
