package com.thlogistic.transportation.transportation.entities;

import com.mongodb.client.model.geojson.Geometry;
import com.thlogistic.transportation.transportation.core.entities.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("garage")
public class GarageEntity {
    @Id
    String id;
    String name;
    Double address;
    Double latitude;
    Double longitude;
}
