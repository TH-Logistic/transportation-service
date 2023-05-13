package com.thlogistic.transportation.config;

import com.thlogistic.transportation.client.auth.AuthorizationClient;
import com.thlogistic.transportation.client.user.UserClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private static String domainUrl = System.getenv("DOMAIN_URL");

    public static final String AUTHORIZATION_BASE_URL = "http://" + domainUrl + ":8000";
    public static final String USER_BASE_URL = "http://" + domainUrl + ":8001";

    @Bean
    public AuthorizationClient authorizationClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(AuthorizationClient.class, AUTHORIZATION_BASE_URL);
    }

    @Bean
    public UserClient userClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(UserClient.class, USER_BASE_URL);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        return modelMapper;
    }
}
