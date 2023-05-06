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

    @GetMapping("/{id}")
    ResponseEntity<Object> getTransportation(@PathVariable String id);
    @GetMapping("/list")
    ResponseEntity<Object> listTransportation(@Valid ListTransportationPagingRequest request);

    @PostMapping
    ResponseEntity<Object> createTransportation(@Valid @RequestBody CreateTransportationRequest request);

    @PutMapping("/{id}")
    ResponseEntity<Object> updateTransportation(@Valid @RequestBody UpdateTransportationRequest request, @PathVariable String id);
}
