package com.thlogistic.transportation.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garage {
    String id;
    String name;
    String address;
    Double latitude;
    Double longitude;
}
