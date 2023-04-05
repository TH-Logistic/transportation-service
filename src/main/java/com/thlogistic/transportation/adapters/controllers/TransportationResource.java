package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.CreateTransportationRequest;
import com.thlogistic.transportation.adapters.dtos.ListTransportationPagingRequest;
import com.thlogistic.transportation.adapters.dtos.UpdateTransportationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transportation")
interface TransportationResource {
    @GetMapping("/list")
    ResponseEntity<Object> listGarage(@Valid ListTransportationPagingRequest request);

    @PostMapping
    ResponseEntity<Object> createGarage(@Valid @RequestBody CreateTransportationRequest request);

    @PutMapping("/{id}")
    ResponseEntity<Object> updateGarage(@Valid @RequestBody UpdateTransportationRequest request, @PathVariable String id);
}
