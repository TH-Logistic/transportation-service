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
@RequestMapping("api/v1/transportation")
interface TransportationResource {

    @GetMapping("/{id}")
    ResponseEntity<Object> getTransportation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String id);

    @GetMapping("detail/{id}")
    ResponseEntity<Object> getTransportationDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String id);

    @GetMapping("/find-by-driver/{driverId}")
    ResponseEntity<Object> getTransportationByDriverId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String driverId);

    @GetMapping("/list")
    ResponseEntity<Object> listTransportation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid ListTransportationPagingRequest request);

    @PostMapping
    ResponseEntity<Object> createTransportation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody CreateTransportationRequest request);

    @PutMapping("/{id}")
    ResponseEntity<Object> updateTransportation(@Valid @RequestBody UpdateTransportationRequest request, @PathVariable String id);

    @PutMapping("/update-delivery-status/{id}")
    ResponseEntity<Object> updateDeliveryStatus(@Valid @RequestBody UpdateTransportationDeliveryStatusRequest request, @PathVariable String id);

    @DeleteMapping("/all")
    ResponseEntity<Object> deleteAll();
}
