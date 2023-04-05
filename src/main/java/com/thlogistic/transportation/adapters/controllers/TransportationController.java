package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.core.usecases.CreateTransportationUseCase;
import com.thlogistic.transportation.core.usecases.ListTransportationUseCase;
import com.thlogistic.transportation.core.usecases.UpdateTransportationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransportationController extends BaseController implements TransportationResource {

    private final CreateTransportationUseCase createTransportationUseCase;

    private final UpdateTransportationUseCase updateTransportationUseCase;

    private final ListTransportationUseCase listTransportationUseCase;

    @Override
    public ResponseEntity<Object> listGarage(ListTransportationPagingRequest request) {
        BasePagingResponse<GetTransportationResponse> result = listTransportationUseCase.execute(request);
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> createGarage(CreateTransportationRequest request) {
        CreateTransportationResponse result = createTransportationUseCase.execute(request);
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> updateGarage(UpdateTransportationRequest request, String id) {
        updateTransportationUseCase.execute(Pair.of(id, request));
        return successResponse(true, null);
    }
}
