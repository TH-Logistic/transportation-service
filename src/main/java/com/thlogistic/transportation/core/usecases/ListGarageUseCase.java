package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.GetGarageResponse;
import com.thlogistic.transportation.adapters.dtos.ListGaragePagingRequest;
import com.thlogistic.transportation.adapters.dtos.BasePagingResponse;
import org.springframework.stereotype.Service;

@Service
public interface ListGarageUseCase extends BaseUseCase<ListGaragePagingRequest, BasePagingResponse<GetGarageResponse>> {
}
