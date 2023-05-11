package com.thlogistic.transportation.adapters.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransportationDeliveryStatusRequest {
    @NotNull(message = "Delivery status must not be null")
    Integer deliveryStatus;
    String garageId;
}
