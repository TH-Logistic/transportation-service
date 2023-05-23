package com.thlogistic.transportation.adapters.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTransportationNoGarageResponse {
    String id;
    String licensePlate;
    Double load;
    String mainDriver;
    String coDriver;
}
