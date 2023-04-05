package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.*;
import org.springframework.stereotype.Service;

@Service
public interface ListTransportationUseCase extends BaseUseCase<ListTransportationPagingRequest, BasePagingResponse<GetTransportationResponse>> {
}
