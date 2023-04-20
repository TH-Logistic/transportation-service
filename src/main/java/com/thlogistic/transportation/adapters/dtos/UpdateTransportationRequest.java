package com.thlogistic.transportation.adapters.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransportationRequest {
    @NotBlank(message = "Invalid license plate")
    String licensePlate;
    @Min(value = 0, message = "Invalid load")
    Double load;
    @NotNull(message = "Delivery status must not be null")
    Integer deliveryStatus;
    String garageId;
    @NotNull(message = "Main driver id must not be null")
    String mainDriverId;
    @NotNull(message = "Co-driver id must not be null")
    String coDriverId;
}
