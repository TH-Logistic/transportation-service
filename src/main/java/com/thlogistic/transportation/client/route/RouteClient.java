package com.thlogistic.transportation.client.route;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import com.thlogistic.transportation.client.job.GetJobStatisticResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface RouteClient {
    @RequestLine("GET api/v1/location/{id}")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"
    })
    BaseResponse<LocationResponse> getLocation(@Param("token") String token, @Param("id") String id);
}
