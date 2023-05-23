package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BasePagingResponse;
import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationWithDriverNameResponse;
import com.thlogistic.transportation.adapters.dtos.ListTransportationPagingRequest;
import org.springframework.stereotype.Service;

@Service
public interface DeleteAllTransportationUseCase extends BaseUseCase<Boolean, Boolean> {
}
