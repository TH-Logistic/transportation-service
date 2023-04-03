package com.thlogistic.transportation.adapters.controllers;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public ResponseEntity<Object> successResponse(Object data, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.ok(body);
    }
}
