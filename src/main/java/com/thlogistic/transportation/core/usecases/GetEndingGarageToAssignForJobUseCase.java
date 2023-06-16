package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetEndingGarageToAssignForJobRequest;
import com.thlogistic.transportation.adapters.dtos.GetEndingGarageToAssignForJobResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetEndingGarageToAssignForJobUseCase extends
        BaseUseCase<
                BaseTokenRequest<GetEndingGarageToAssignForJobRequest>,
                List<GetEndingGarageToAssignForJobResponse>> {
}
