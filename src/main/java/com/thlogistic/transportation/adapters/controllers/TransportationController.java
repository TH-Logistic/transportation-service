package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.core.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransportationController extends BaseController implements TransportationResource {

    private final GetTransportationUseCase getTransportationUseCase;
    private final GetTransportationDetailUseCase getTransportationDetailUseCase;
    private final GetTransportationByDriverIdUseCase getTransportationByDriverIdUseCase;
    private final GetTotalTransportationUseCase getTotalTransportationUseCase;
    private final CreateTransportationUseCase createTransportationUseCase;
    private final UpdateTransportationUseCase updateTransportationUseCase;
    private final ListTransportationUseCase listTransportationUseCase;
    private final UpdateTransportationDeliveryStatusUseCase updateTransportationDeliveryStatusUseCase;
    private final DeleteAllTransportationUseCase deleteAllTransportationUseCase;

    @Override
    public ResponseEntity<Object> getTransportation(String token, String id) {
        GetTransportationResponse result = getTransportationUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        id
                )
        );
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> getTransportationDetail(String token, String id) {
        GetTransportationDetailResponse result = getTransportationDetailUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        id
                )
        );
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> getTransportationByDriverId(String token, String driverId) {
        GetTransportationResponse result = getTransportationByDriverIdUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        driverId
                )
        );
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> listTransportation(String token, ListTransportationPagingRequest request) {
        BasePagingResponse<GetTransportationWithDriverNameResponse> result = listTransportationUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        request
                )
        );
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> getTotalTransportations(String token) {
        Integer result = getTotalTransportationUseCase.execute(token);
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> createTransportation(String token, CreateTransportationRequest request) {
        CreateTransportationResponse result = createTransportationUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        request
                )
        );
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> updateTransportation(UpdateTransportationRequest request, String id) {
        updateTransportationUseCase.execute(Pair.of(id, request));
        return successResponse(true, null);
    }

    @Override
    public ResponseEntity<Object> updateDeliveryStatus(UpdateTransportationDeliveryStatusRequest request, String id) {
        updateTransportationDeliveryStatusUseCase.execute(Pair.of(id, request));
        return successResponse(true, null);
    }

    @Override
    public ResponseEntity<Object> deleteAll() {
        deleteAllTransportationUseCase.execute(true);
        return successResponse(true, null);
    }
}
