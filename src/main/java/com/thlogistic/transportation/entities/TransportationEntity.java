package com.thlogistic.transportation.entities;

import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Transportation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
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

    public TransportationEntity(String licensePlate, Double load, String garageId, DeliveryStatus deliveryStatus, String mainDriverId, String coDriverId) {
        this.licensePlate = licensePlate;
        this.load = load;
        this.garageId = garageId;
        this.deliveryStatus = deliveryStatus;
        this.mainDriverId = mainDriverId;
        this.coDriverId = coDriverId;
    }

    public Transportation toTransportation() {
        return new Transportation(
                this.id,
                this.licensePlate,
                this.load,
                this.garageId,
                this.deliveryStatus,
                this.mainDriverId,
                this.coDriverId
        );
    }
}
