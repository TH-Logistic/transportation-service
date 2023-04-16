package com.thlogistic.transportation.adapters.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetTransportationResponse {
    String id;
    String licensePlate;
    Double load;
    GetGarageResponse garage;
    String mainDriverName;
    String coDriverName;
}
