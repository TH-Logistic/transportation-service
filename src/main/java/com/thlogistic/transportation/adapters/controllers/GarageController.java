package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.core.usecases.CreateGarageUseCase;
import com.thlogistic.transportation.core.usecases.ListGarageUseCase;
import com.thlogistic.transportation.core.usecases.UpdateGarageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GarageController extends BaseController implements GarageResource {

    private final CreateGarageUseCase createGarageUseCase;
    private final UpdateGarageUseCase updateGarageUseCase;
    private final ListGarageUseCase listGarageUseCase;

    @Override
    public ResponseEntity<Object> listGarage(ListGaragePagingRequest request) {
        BasePagingResponse<GetGarageResponse> result = listGarageUseCase.execute(request);
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> createGarage(CreateGarageRequest request) {
        CreateGarageResponse result = createGarageUseCase.execute(request);
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> updateGarage(UpdateGarageRequest request, String id) {
        updateGarageUseCase.execute(Pair.of(id, request));
        return successResponse(true, null);
    }
}