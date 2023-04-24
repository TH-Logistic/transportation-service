package com.thlogistic.transportation.adapters.controllers;

import com.thlogistic.transportation.adapters.dtos.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public ResponseEntity<Object> successResponse(Object data, String message) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
}
