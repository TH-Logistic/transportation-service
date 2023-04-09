package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BasePagingResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.adapters.dtos.ListTransportationPagingRequest;
import com.thlogistic.transportation.adapters.repositories.BasePagingQueryResult;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import com.thlogistic.transportation.mapper.TransportationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTransportationUseCaseImpl implements ListTransportationUseCase {

    private final TransportationRepository repository;
    private final GetGarageUseCase getGarageUseCase;

    @Override
    public BasePagingResponse<GetTransportationResponse> execute(ListTransportationPagingRequest request) {
        BasePagingQueryResult<List<TransportationEntity>> queryResult;
        Integer deliverStatusCode = request.getDeliveryStatus();
        if (deliverStatusCode == null) {
            queryResult = repository.listWithoutDeliveryStatus(request.getKeyword(),
                    request.getPage(),
                    request.getSize());
        }
        else {
            queryResult = repository.list(
                    request.getKeyword(),
                    DeliveryStatus.fromInt(request.getDeliveryStatus()),
                    request.getPage(),
                    request.getSize()
            );
        }

        // Update Garage if Transportation contains non-null garageId
        List<GetTransportationResponse> transportationResponses = new LinkedList<>();
        queryResult.getData().forEach(transportationEntity -> {
                    if (transportationEntity.getGarageId() != null) {
                        Garage garage = getGarageUseCase.execute(transportationEntity.getGarageId());
                        GetTransportationResponse getTransportationResponse = TransportationMapper.toGetTransportationResponse(
                                transportationEntity.toTransportation(),
                                garage
                        );
                        transportationResponses.add(getTransportationResponse);
                    } else {
                        GetTransportationResponse getTransportationResponse = TransportationMapper.toGetTransportationResponse(
                                transportationEntity.toTransportation(),
                                null
                        );
                        transportationResponses.add(getTransportationResponse);
                    }
                }
        );

        BasePagingResponse<GetTransportationResponse> response = new BasePagingResponse<>();
        response.setContent(transportationResponses);
        response.setTotal(queryResult.getTotal());
        response.setTotalPage(queryResult.getTotalPage());
        return response;
    }
}