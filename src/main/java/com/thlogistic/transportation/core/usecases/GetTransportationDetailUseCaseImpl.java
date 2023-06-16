package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.aop.exception.CustomRuntimeException;
import com.thlogistic.transportation.client.job.GetJobListDto;
import com.thlogistic.transportation.client.job.GetJobStatisticResponse;
import com.thlogistic.transportation.client.job.JobClient;
import com.thlogistic.transportation.client.job.JobStatisticDto;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        String mainDriverId;
        try {
            mainDriverId = transportationDto.getMainDriver().getId();
            getJobStatisticResponse = jobClient.getJobStatistic(token, mainDriverId);
        } catch (Exception e) {
            throw new CustomRuntimeException("An error occurred when loading job statistic");
        }

        String orderId = null;
        if (Objects.equals(transportationDto.getDeliveryStatus(), DeliveryStatus.DELIVERY.status)
                || !transportationDto.getIsInGarage()) {
            try {
                orderId = jobClient.getCurrentDeliveryJobOfDriver(token, mainDriverId).getData();
            } catch (Exception e) {
                throw new CustomRuntimeException("An error occurred when loading order ID");
            }
        }

        JobStatisticDto jobStatisticDto = getJobStatisticResponse.getData().getStatistic();
        List<GetJobListDto> jobListDtos = getJobStatisticResponse.getData().getJobs();

        for (GetJobListDto dto : jobListDtos) {
            dto.setLicensePlate(transportationDto.getLicensePlate());
            dto.setDriverInCharge(transportationDto.getMainDriver().getName());
        }

        return new GetTransportationDetailResponse(
                mapToResponseWithOrderId(transportationDto, orderId),
                jobStatisticDto,
                jobListDtos
        );
    }

    private GetTransportationWithOrderIdResponse mapToResponseWithOrderId(
            GetTransportationResponse originalResponse,
            String orderId
            ) {
        return new GetTransportationWithOrderIdResponse(
                originalResponse.getId(),
                orderId,
                originalResponse.getLicensePlate(),
                originalResponse.getLoad(),
                originalResponse.getDeliveryStatus(),
                originalResponse.getIsInGarage(),
                originalResponse.getGarage(),
                originalResponse.getMainDriver(),
                originalResponse.getCoDriver()
        );
    }
}
