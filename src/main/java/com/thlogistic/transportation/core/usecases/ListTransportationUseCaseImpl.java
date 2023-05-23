package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.adapters.repositories.BasePagingQueryResult;
import com.thlogistic.transportation.client.user.UserClient;
import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import com.thlogistic.transportation.mapper.TransportationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTransportationUseCaseImpl implements ListTransportationUseCase {

    private final TransportationRepository repository;
    private final GetGarageUseCase getGarageUseCase;
    private final UserClient userClient;

    @Override
    public BasePagingResponse<GetTransportationWithDriverNameResponse> execute(BaseTokenRequest<ListTransportationPagingRequest> baseTokenRequest) {
        String token = baseTokenRequest.getToken();
        ListTransportationPagingRequest requestContent = baseTokenRequest.getRequestContent();

        BasePagingQueryResult<List<TransportationEntity>> queryResult;
        Integer deliverStatusCode = requestContent.getDeliveryStatus();
        if (deliverStatusCode == null) {
            queryResult = repository.pagingWithoutDeliveryStatus(requestContent.getKeyword(),
                    requestContent.getPage(),
                    requestContent.getSize());
        } else {
            queryResult = repository.paging(
                    requestContent.getKeyword(),
                    DeliveryStatus.fromInt(requestContent.getDeliveryStatus()),
                    requestContent.getPage(),
                    requestContent.getSize()
            );
        }

        List<GetTransportationWithDriverNameResponse> transportationResponses = queryResult.getData().stream().map(entity -> {
            String mainDriverId = entity.getMainDriverId();
            String coDriverId = entity.getCoDriverId();

            UserInfoDto mainDriverDto;
            UserInfoDto coDriverDto;

            try {
                mainDriverDto = userClient.getUser(token, mainDriverId).getData();
                coDriverDto = userClient.getUser(token, coDriverId).getData();
            } catch (Exception e) {
                throw new RuntimeException("An error occurred when get driver info");
            }

            if (entity.getDeliveryStatus() == DeliveryStatus.IDLE) {
                Garage garage = getGarageUseCase.execute(entity.getGarageId());
                return TransportationMapper.toGetTransportationWithDriverNameResponse(
                        entity.toTransportation(),
                        garage,
                        mainDriverDto,
                        coDriverDto
                );
            }
            return TransportationMapper.toGetTransportationWithDriverNameResponse(
                    entity.toTransportation(),
                    null,
                    mainDriverDto,
                    coDriverDto
            );
        }).toList();

        BasePagingResponse<GetTransportationWithDriverNameResponse> response = new BasePagingResponse<>();
        response.setContent(transportationResponses);
        response.setTotal(queryResult.getTotal());
        response.setTotalPage(queryResult.getTotalPage());
        return response;
    }
}
