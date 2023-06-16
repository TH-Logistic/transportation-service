package com.thlogistic.transportation.adapters.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEndingGarageToAssignForJobRequest {
    @NotEmpty(message = "Invalid starting location ID")
    String startingLocationId;
    String keyword;
}
