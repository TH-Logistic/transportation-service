package com.thlogistic.transportation.adapters.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransportationRequest {
    @NotBlank(message = "Invalid license plate")
    String licensePlate;
    @Min(value = 0, message = "Invalid load")
    Double load;
    @NotBlank(message = "Invalid garage ID")
    String garageId;
    @NotBlank(message = "Invalid main driver ID")
    String mainDriverId;
    @NotBlank(message = "Invalid co driver ID")
    String coDriverId;
}
