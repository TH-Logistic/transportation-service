package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.GetGarageResponse;
import com.thlogistic.transportation.adapters.dtos.ListGaragePagingRequest;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.adapters.dtos.BasePagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListGarageUseCaseImpl implements ListGarageUseCase {

    private final GarageRepository repository;

    @Override
    public BasePagingResponse<GetGarageResponse> execute(ListGaragePagingRequest listGaragePagingRequest) {
        return null;
    }
}
