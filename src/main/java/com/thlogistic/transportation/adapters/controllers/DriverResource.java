package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.CreateTransportationRequest;
import com.thlogistic.transportation.adapters.dtos.ListTransportationPagingRequest;
import com.thlogistic.transportation.adapters.dtos.UpdateTransportationDeliveryStatusRequest;
import com.thlogistic.transportation.adapters.dtos.UpdateTransportationRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/driver")
interface DriverResource {

    @GetMapping("/{id}")
    ResponseEntity<Object> getDriver(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String id);

    @GetMapping("detail/{id}")
    ResponseEntity<Object> getDriverDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String id);
}
