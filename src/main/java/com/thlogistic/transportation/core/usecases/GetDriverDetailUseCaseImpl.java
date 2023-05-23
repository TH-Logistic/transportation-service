package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.aop.exception.CustomRuntimeException;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.client.job.GetJobListDto;
import com.thlogistic.transportation.client.job.GetJobStatisticResponse;
import com.thlogistic.transportation.client.job.JobClient;
import com.thlogistic.transportation.client.job.JobStatisticDto;
import com.thlogistic.transportation.client.user.UserClient;
import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.Transportation;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetDriverDetailUseCaseImpl implements GetDriverDetailUseCase {
    private final TransportationRepository transportationRepository;
    private final JobClient jobClient;
    private final UserClient userClient;

    @Override
    public GetDriverDetailResponse execute(BaseTokenRequest<String> baseTokenRequest) {
        String token = baseTokenRequest.getToken();
        String driverId = baseTokenRequest.getRequestContent();

        Optional<TransportationEntity> entityOptional = transportationRepository.findByDriverId(driverId);
        if (entityOptional.isEmpty()) {
            throw new DataNotFoundException(Transportation.class.getSimpleName());
        }
        TransportationEntity entity = entityOptional.get();

        UserInfoDto driverDto;

        try {
            driverDto = userClient.getUser(token, driverId).getData();
        } catch (Exception e) {
            throw new CustomRuntimeException("An error occurred when loading statistic for driver detail");
        }

        BaseResponse<GetJobStatisticResponse> getJobStatisticResponse;
        try {
            getJobStatisticResponse = jobClient.getJobStatistic(token, driverId);
        } catch (Exception e) {
            throw new CustomRuntimeException("An error occurred when loading statistic for transportation detail");
        }

        JobStatisticDto jobStatisticDto = getJobStatisticResponse.getData().getStatistic();
        List<GetJobListDto> jobListDtos = getJobStatisticResponse.getData().getJobs();

        for (GetJobListDto dto : jobListDtos) {
            dto.setLicensePlate(entity.getLicensePlate());
            dto.setDriverInCharge(driverDto.getName());
        }

        return new GetDriverDetailResponse(
                driverDto,
                jobStatisticDto,
                jobListDtos
        );
    }
}
