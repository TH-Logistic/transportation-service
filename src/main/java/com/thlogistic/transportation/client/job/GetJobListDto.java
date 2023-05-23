package com.thlogistic.transportation.client.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetJobListDto {
    String id;
    String licensePlate;
    String driverInCharge;
    List<String> products;
    String createdAt;
    String pickUpAt;
    String unloadAt;
    Double orderFee;
    Integer status;
}
