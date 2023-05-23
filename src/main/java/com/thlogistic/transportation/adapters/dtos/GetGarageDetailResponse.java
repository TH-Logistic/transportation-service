package com.thlogistic.transportation.adapters.dtos;

import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.Garage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGarageDetailResponse {
    Garage garage;
    UserInfoDto manager;
    List<GetTransportationNoGarageResponse> transportationsAtGarage;
}
