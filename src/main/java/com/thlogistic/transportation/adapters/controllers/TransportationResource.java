package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.*;
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

    @GetMapping("/for-assigning")
    ResponseEntity<Object> getTransportationForAssigning(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid GetTransportationToAssignForJobRequest request);

    @GetMapping("/find-by-driver/{driverId}")
    ResponseEntity<Object> getTransportationByDriverId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String driverId);

    @GetMapping("/list")
    ResponseEntity<Object> listTransportation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid ListTransportationPagingRequest request);

    @GetMapping("/total")
    ResponseEntity<Object> getTotalTransportations(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @PostMapping
    ResponseEntity<Object> createTransportation(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody CreateTransportationRequest request);

    @PutMapping("/{id}")
    ResponseEntity<Object> updateTransportation(@Valid @RequestBody UpdateTransportationRequest request, @PathVariable String id);

    @PutMapping("/update-delivery-status/{id}")
    ResponseEntity<Object> updateDeliveryStatus(@Valid @RequestBody UpdateTransportationDeliveryStatusRequest request, @PathVariable String id);

    @DeleteMapping("/all")
    ResponseEntity<Object> deleteAll();
}
