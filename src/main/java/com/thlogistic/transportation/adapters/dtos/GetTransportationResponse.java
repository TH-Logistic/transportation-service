package com.thlogistic.transportation.adapters.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTransportationResponse {
    String id;
    String licensePlate;
    Double load;
    Integer deliveryStatus;
    GetGarageResponse garage;
    GetDriverInfoDto mainDriverInfo;
    GetDriverInfoDto coDriverInfo;
}
