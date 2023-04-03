package com.thlogistic.transportation.transportation.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garage {
    String id;
    String name;
    Double address;
    Double latitude;
    Double longitude;
}
