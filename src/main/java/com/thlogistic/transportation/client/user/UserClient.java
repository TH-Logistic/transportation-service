package com.thlogistic.transportation.client.user;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface UserClient {

    @RequestLine("GET /api/users/{id}")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"
    })
    BaseResponse<UserInfoDto> getUser(@Param("token") String token, @Param("id") String id);
}
