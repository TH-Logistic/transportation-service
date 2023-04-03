package com.thlogistic.transportation.adapters.dtos;

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
    GetGarageResponse garage;
    String mainDriverName;
    String coDriverName;
}
