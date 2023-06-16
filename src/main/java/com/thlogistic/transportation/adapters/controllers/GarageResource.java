package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/garage")
interface GarageResource {
    @GetMapping("/{id}")
    ResponseEntity<Object> getGarage(@PathVariable String id);

    @GetMapping("/detail/{id}")
    ResponseEntity<Object> getGarageDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String id);

    @GetMapping("/ending/for-assigning")
    ResponseEntity<Object> getEndingGarageForAssigning(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid GetEndingGarageToAssignForJobRequest request);

    @GetMapping("/list")
    ResponseEntity<Object> listGarage(@Valid ListGaragePagingRequest request);

    @PostMapping
    ResponseEntity<Object> createGarage(@Valid @RequestBody CreateGarageRequest request);

    @PutMapping("/{id}")
    ResponseEntity<Object> updateGarage(@Valid @RequestBody UpdateGarageRequest request, @PathVariable String id);
}
