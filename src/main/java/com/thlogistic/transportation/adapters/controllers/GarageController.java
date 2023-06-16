package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.usecases.*;
import com.thlogistic.transportation.mapper.GarageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GarageController extends BaseController implements GarageResource {

    private final CreateGarageUseCase createGarageUseCase;
    private final UpdateGarageUseCase updateGarageUseCase;
    private final ListGarageUseCase listGarageUseCase;
    private final GetGarageUseCase getGarageUseCase;
    private final GetEndingGarageToAssignForJobUseCase getEndingGarageToAssignForJobUseCase;
    private final GetGarageDetailUseCase getGarageDetailUseCase;

    @Override
    public ResponseEntity<Object> getGarage(String id) {
        Garage garage = getGarageUseCase.execute(id);
        return successResponse(GarageMapper.fromGarageToResponse(garage), null);
    }

    @Override
    public ResponseEntity<Object> getGarageDetail(String token, String id) {
        GetGarageDetailResponse result = getGarageDetailUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        id
                )
        );
        return successResponse(result, null);
    }

    @Override
    public ResponseEntity<Object> getEndingGarageForAssigning(String token, GetEndingGarageToAssignForJobRequest request) {
        List<GetEndingGarageToAssignForJobResponse> result = getEndingGarageToAssignForJobUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        request
                )
        );
        return successResponse(result, null);
    }

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
