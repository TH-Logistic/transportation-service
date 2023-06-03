package com.thlogistic.transportation.adapters.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTransportationToAssignForJobRequest {
    @NotEmpty(message = "Invalid starting location ID")
    String startingLocationId;
    String keyword;
}
