package com.thlogistic.transportation.client.google_map;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thlogistic.transportation.aop.exception.CustomRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistanceMatrixDto {
    List<String> destinationAddresses;
    List<String> originAddresses;
    List<RowItemDto> rows;

    public Double getDistance() {
        try {
            return rows.get(0).elements.get(0).distance.value / 1000.0; // Convert to km
        } catch (Exception e) {
            throw new CustomRuntimeException("Google Map API error");
        }
    }
}
