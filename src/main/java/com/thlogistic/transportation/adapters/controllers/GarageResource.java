package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.CreateGarageRequest;
import com.thlogistic.transportation.adapters.dtos.UpdateGarageRequest;
import com.thlogistic.transportation.adapters.dtos.ListGaragePagingRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/garage")
interface GarageResource {
    @GetMapping("/{id}")
    ResponseEntity<Object> getGarage(@PathVariable String id);
    @GetMapping("/list")
    ResponseEntity<Object> listGarage(@Valid ListGaragePagingRequest request);

    @PostMapping
    ResponseEntity<Object> createGarage(@Valid @RequestBody CreateGarageRequest request);

    @PutMapping("/{id}")
    ResponseEntity<Object> updateGarage(@Valid @RequestBody UpdateGarageRequest request, @PathVariable String id);
}
