package com.thlogistic.transportation.entities;

import com.thlogistic.transportation.core.entities.Garage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("garage")
public class GarageEntity {
    @Id
    String id;
    String name;
    String address;
    Double latitude;
    Double longitude;

    public GarageEntity(String name, String address, Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Garage toGarage() {
        return new Garage(
                this.id,
                this.name,
                this.address,
                this.latitude,
                this.longitude
        );
    }
}
