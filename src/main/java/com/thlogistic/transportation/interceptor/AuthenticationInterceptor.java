package com.thlogistic.transportation.interceptor;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import com.thlogistic.transportation.aop.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_URL = "http://www.thinhlh.com:8000/check-permissions";
    private final RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (token != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(AUTHORIZATION_HEADER, token);

            List<String> body = List.of("admin");

            HttpEntity<List<String>> authRequest = new HttpEntity<>(body, headers);

            try {
                ResponseEntity<BaseResponse> authResponse = restTemplate.postForEntity(AUTHORIZATION_URL, authRequest, BaseResponse.class);
                if (authResponse.getBody() == null || !authResponse.getBody().getSuccess()) {
                    throw new UnauthorizedException("Invalid token credential");
                }
            } catch (Exception e) {
                System.out.println("DebugMode: " + e.getMessage());
                throw new UnauthorizedException("Invalid token credential");
            }
        } else {
            throw new UnauthorizedException("Unauthenticated");
        }
        return true;
    }
}
