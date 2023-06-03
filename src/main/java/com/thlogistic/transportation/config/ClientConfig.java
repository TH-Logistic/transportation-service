package com.thlogistic.transportation.config;

import com.thlogistic.transportation.client.auth.AuthorizationClient;
import com.thlogistic.transportation.client.google_map.GoogleMapClient;
import com.thlogistic.transportation.client.job.JobClient;
import com.thlogistic.transportation.client.route.RouteClient;
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
public class ClientConfig {

    private static final String httpPath = "http://";
    private static final String httpsPath = "https://";

    //region internal services
    private static final String domainUrl = System.getenv("DOMAIN_URL");
    public static final String AUTHORIZATION_BASE_URL = httpPath + domainUrl + ":8000";
    public static final String JOB_BASE_URL = httpPath + domainUrl + ":8085";
    public static final String ROUTE_BASE_URL = httpPath + domainUrl + ":8083";
    public static final String USER_BASE_URL = httpPath + domainUrl + ":8001";
    //endregion

    //region external services
    private static final String GOOGLE_MAP_BASE_URL = httpsPath + "maps.googleapis.com/maps";
    public static final String GOOGLE_MAP_API_KEY = System.getenv("GOOGLE_MAP_API_KEY");
    //endregion

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
    public JobClient jobClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(JobClient.class, JOB_BASE_URL);
    }

    @Bean
    public RouteClient routeClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(RouteClient.class, ROUTE_BASE_URL);
    }

    @Bean
    public GoogleMapClient googleMapClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(GoogleMapClient.class, GOOGLE_MAP_BASE_URL);
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
