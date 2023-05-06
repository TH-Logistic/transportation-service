package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.core.usecases.CreateTransportationUseCase;
import com.thlogistic.transportation.core.usecases.GetTransportationUseCase;
import com.thlogistic.transportation.core.usecases.ListTransportationUseCase;
import com.thlogistic.transportation.core.usecases.UpdateTransportationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransportationController extends BaseController implements TransportationResource {

    private final GetTransportationUseCase getTransportationUseCase;

    private final CreateTransportationUseCase createTransportationUseCase;

    private final UpdateTransportationUseCase updateTransportationUseCase;

    private final ListTransportationUseCase listTransportationUseCase;

    @Override
    public ResponseEntity<Object> getTransportation(String id) {
        GetTransportationResponse response = getTransportationUseCase.execute(id);
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
}
