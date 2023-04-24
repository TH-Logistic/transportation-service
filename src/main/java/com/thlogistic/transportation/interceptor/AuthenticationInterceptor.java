package com.thlogistic.transportation.interceptor;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import com.thlogistic.transportation.aop.exception.UnauthorizedException;
import com.thlogistic.transportation.client.AuthorizationClient;
import com.thlogistic.transportation.client.PermissionDto;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_URL = "http://www.thinhlh.com:8000/check-permissions";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String token = request.getHeader(AUTHORIZATION_HEADER);
        List<String> roles = List.of("admin");

        if (token != null) {
            AuthorizationClient authorizationClient = Feign.builder()
                    .client(new OkHttpClient())
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .target(AuthorizationClient.class, AUTHORIZATION_URL);
            BaseResponse<PermissionDto> permissionResponse = authorizationClient.checkPermission(token, roles);
            if (!permissionResponse.getSuccess()) {
                throw new UnauthorizedException("Invalid token credential");
            }
        } else {
            throw new UnauthorizedException("Invalid token credential");
        }
        return true;
    }
}
