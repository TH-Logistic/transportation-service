package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationNoDriverInfoResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import org.springframework.stereotype.Service;

@Service
public interface GetTransportationByDriverIdUseCase extends BaseUseCase<BaseTokenRequest<String>, GetTransportationResponse> {
}
