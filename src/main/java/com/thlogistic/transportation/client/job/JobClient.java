package com.thlogistic.transportation.client.job;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface JobClient {
    @RequestLine("GET api/v1/job/statistic/driver/{driverId}")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"
    })
    BaseResponse<GetJobStatisticResponse> getJobStatistic(@Param("token") String token, @Param("driverId") String driverId);

    @RequestLine("GET api/v1/job/number-of-trips/{driverId}")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"
    })
    BaseResponse<Integer> getNumberOfTripsOfDriver(@Param("token") String token, @Param("driverId") String driverId);

    @RequestLine("GET api/v1/job/current-delivery/{driverId}")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"
    })
    BaseResponse<String> getCurrentDeliveryJobOfDriver(@Param("token") String token, @Param("driverId") String driverId);
}
