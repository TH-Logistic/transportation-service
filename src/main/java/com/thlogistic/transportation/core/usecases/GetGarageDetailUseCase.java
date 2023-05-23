package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetGarageDetailResponse;
import com.thlogistic.transportation.core.entities.Garage;
import org.springframework.stereotype.Service;

@Service
public interface GetGarageDetailUseCase extends BaseUseCase<BaseTokenRequest<String>, GetGarageDetailResponse> {
}
