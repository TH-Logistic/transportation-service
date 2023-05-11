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
    private final GetTransportationByDriverIdUseCase getTransportationByDriverIdUseCase;
    private final CreateTransportationUseCase createTransportationUseCase;
    private final UpdateTransportationUseCase updateTransportationUseCase;
    private final ListTransportationUseCase listTransportationUseCase;
    private final UpdateTransportationDeliveryStatusUseCase updateTransportationDeliveryStatusUseCase;

    @Override
    public ResponseEntity<Object> getTransportation(String id) {
        GetTransportationResponse response = getTransportationUseCase.execute(id);
        return successResponse(response, null);
    }

    @Override
    public ResponseEntity<Object> getTransportationByDriverId(String token, String driverId) {
        GetTransportationIdAndLicensePlateResponse response = getTransportationByDriverIdUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        driverId
                )
        );
        return successResponse(response, null);
    }

    @Override
    public ResponseEntity<Object> listTransportation(ListTransportationPagingRequest request) {
        BasePagingResponse<GetTransportationResponse> result = listTransportationUseCase.execute(request);
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> createTransportation(CreateTransportationRequest request) {
        CreateTransportationResponse result = createTransportationUseCase.execute(request);
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
}
