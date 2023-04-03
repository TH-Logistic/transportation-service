package com.thlogistic.transportation.core.usecases;

public interface BaseUseCase<Request, Response> {
    Response execute(Request request);
}
