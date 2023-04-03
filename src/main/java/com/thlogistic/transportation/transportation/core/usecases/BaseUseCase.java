package com.thlogistic.transportation.transportation.core.usecases;

public interface BaseUseCase<Request, Response> {
    Response execute(Request request);
}
