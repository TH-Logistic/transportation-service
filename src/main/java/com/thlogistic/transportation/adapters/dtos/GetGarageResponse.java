package com.thlogistic.transportation.adapters.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGarageResponse {
    String id;
    String name;
    String address;
    Double latitude;
    Double longitude;
}
