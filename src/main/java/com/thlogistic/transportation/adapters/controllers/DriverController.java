package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetDriverDetailResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationDetailResponse;
import com.thlogistic.transportation.core.usecases.GetDriverDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DriverController extends BaseController implements DriverResource {

    private final GetDriverDetailUseCase getDriverDetailUseCase;

    @Override
    public ResponseEntity<Object> getDriver(String token, String id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getDriverDetail(String token, String id) {
        GetDriverDetailResponse result = getDriverDetailUseCase.execute(
                new BaseTokenRequest<>(
                        token,
                        id
                )
        );
        return successResponse(result, null);
    }
}
