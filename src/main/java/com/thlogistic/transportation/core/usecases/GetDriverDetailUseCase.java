package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetDriverDetailResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationDetailResponse;
import org.springframework.stereotype.Service;

@Service
public interface GetDriverDetailUseCase extends BaseUseCase<BaseTokenRequest<String>, GetDriverDetailResponse> {
}
