package com.thlogistic.transportation.transportation.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
