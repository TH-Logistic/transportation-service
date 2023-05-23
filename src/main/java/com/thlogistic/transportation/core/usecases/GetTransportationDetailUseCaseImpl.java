package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationDetailResponse;
import com.thlogistic.transportation.adapters.dtos.GetTransportationResponse;
import com.thlogistic.transportation.aop.exception.CustomRuntimeException;
import com.thlogistic.transportation.aop.exception.DataNotFoundException;
import com.thlogistic.transportation.client.job.GetJobListDto;
import com.thlogistic.transportation.client.job.GetJobStatisticResponse;
import com.thlogistic.transportation.client.job.JobClient;
import com.thlogistic.transportation.client.job.JobStatisticDto;
import com.thlogistic.transportation.client.user.UserClient;
import com.thlogistic.transportation.client.user.UserInfoDto;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.entities.Transportation;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.TransportationEntity;
import com.thlogistic.transportation.mapper.TransportationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTransportationDetailUseCaseImpl implements GetTransportationDetailUseCase {
    private final GetTransportationUseCase getTransportationUseCase;
    private final JobClient jobClient;

    @Override
    public GetTransportationDetailResponse execute(BaseTokenRequest<String> baseTokenRequest) {
        String token = baseTokenRequest.getToken();

        GetTransportationResponse transportationDto = getTransportationUseCase.execute(baseTokenRequest);

        BaseResponse<GetJobStatisticResponse> getJobStatisticResponse;
        try {
            String mainDriverId = transportationDto.getMainDriver().getId();
            getJobStatisticResponse = jobClient.getJobStatistic(token, mainDriverId);
        } catch (Exception e) {
            throw new CustomRuntimeException("An error occurred when loading statistic for transportation detail");
        }

        JobStatisticDto jobStatisticDto = getJobStatisticResponse.getData().getStatistic();
        List<GetJobListDto> jobListDtos = getJobStatisticResponse.getData().getJobs();

        for (GetJobListDto dto : jobListDtos) {
            dto.setLicensePlate(transportationDto.getLicensePlate());
            dto.setDriverInCharge(transportationDto.getMainDriver().getName());
        }

        return new GetTransportationDetailResponse(
                transportationDto,
                jobStatisticDto,
                jobListDtos
        );
    }
}
