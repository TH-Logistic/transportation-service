package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.core.entities.Garage;
import org.springframework.stereotype.Service;

@Service
public interface GetTransportationUseCase extends BaseUseCase<String, GetTransportationResponse> {
}
