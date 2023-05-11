package com.thlogistic.transportation.adapters.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTransportationIdAndLicensePlateResponse {
    String id;
    String licensePlate;
}
