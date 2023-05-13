package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.CreateTransportationRequest;
import com.thlogistic.transportation.adapters.dtos.CreateTransportationResponse;

public interface CreateTransportationUseCase extends BaseUseCase<BaseTokenRequest<CreateTransportationRequest>, CreateTransportationResponse> {
}
