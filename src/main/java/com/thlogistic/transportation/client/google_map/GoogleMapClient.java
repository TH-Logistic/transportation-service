package com.thlogistic.transportation.client.google_map;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface GoogleMapClient {
    @RequestLine("GET /api/distancematrix/json?origins={origins}&destinations={destinations}&key={key}")
    DistanceMatrixDto getDistanceBetweenTwoLocation(@Param("origins") String origins,
                                                    @Param("destinations") String destinations,
                                                    @Param("key") String key);
}
