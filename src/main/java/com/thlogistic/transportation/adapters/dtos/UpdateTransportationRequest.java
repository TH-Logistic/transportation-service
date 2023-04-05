package com.thlogistic.transportation.adapters.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    Integer deliveryStatus;
    String garageId;
    String mainDriverId;
    String coDriverId;
}
