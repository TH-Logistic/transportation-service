package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationDetailResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import org.springframework.stereotype.Service;

@Service
public interface GetTransportationDetailUseCase extends BaseUseCase<BaseTokenRequest<String>, GetTransportationDetailResponse> {
}
