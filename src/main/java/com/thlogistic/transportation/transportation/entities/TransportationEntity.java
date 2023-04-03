package com.thlogistic.transportation.transportation.entities;

import com.thlogistic.transportation.transportation.core.entities.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("transportation")
public class TransportationEntity {
    @Id
    String id;
    String licensePlate;
    Double load;
    String garageId;
    DeliveryStatus deliveryStatus;
    String mainDriverId;
    String coDriverId;

}
