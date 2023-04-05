package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.core.entities.Garage;
import org.springframework.stereotype.Service;

@Service
public interface GetGarageUseCase extends BaseUseCase<String, Garage> {
}
