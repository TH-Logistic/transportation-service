package com.thlogistic.transportation.client.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobStatisticDto {
    Integer totalTripBasedJob;
    Integer totalTonBasedJob;
    Double totalDistance;
    Double totalWeight;
}
