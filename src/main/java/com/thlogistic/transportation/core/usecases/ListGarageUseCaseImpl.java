package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.GetGarageResponse;
import com.thlogistic.transportation.adapters.dtos.ListGaragePagingRequest;
import com.thlogistic.transportation.adapters.repositories.BasePagingQueryResult;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.adapters.dtos.BasePagingResponse;
import com.thlogistic.transportation.entities.GarageEntity;
import com.thlogistic.transportation.mapper.GarageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListGarageUseCaseImpl implements ListGarageUseCase {

    private final GarageRepository repository;

    @Override
    public BasePagingResponse<GetGarageResponse> execute(ListGaragePagingRequest request) {
        BasePagingQueryResult<List<GarageEntity>> queryResult =
                repository.list(request.getKeyword(), request.getPage(), request.getSize());

        BasePagingResponse<GetGarageResponse> response = new BasePagingResponse<>();
        response.setContent(queryResult.getData().stream().map(GarageMapper::fromGarageEntityToResponse).toList());
        response.setTotal(queryResult.getTotal());
        response.setTotalPage(queryResult.getTotalPage());
        return response;
    }
}
