package com.thlogistic.transportation.client.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {
    String id;
    String name;
    String address;
    Double latitude;
    Double longitude;
}
