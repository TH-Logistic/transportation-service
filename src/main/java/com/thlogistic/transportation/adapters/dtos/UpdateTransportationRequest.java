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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateTransportationRequest {
    @NotBlank(message = "Invalid license plate")
    String licensePlate;
    @Min(value = 0, message = "Invalid load")
    Double load;
    Integer deliveryStatus;
    String garageId;
    String mainDriverId;
    String coDriverId;
}
