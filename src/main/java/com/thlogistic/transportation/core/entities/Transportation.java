package com.thlogistic.transportation.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportation {
    String id;
    String licensePlate;
    Double load;
    String garageId;
    DeliveryStatus deliveryStatus;
    String mainDriverId;
    String coDriverId;

}
