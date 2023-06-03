package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationToAssignForJobRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationToAssignForJobResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetTransportationToAssignForJobUseCase extends
        BaseUseCase<
                BaseTokenRequest<GetTransportationToAssignForJobRequest>,
                List<GetTransportationToAssignForJobResponse>> {
}
