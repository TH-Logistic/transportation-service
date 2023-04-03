package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.UpdateGarageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public interface UpdateGarageUseCase extends BaseUseCase<Pair<String, UpdateGarageRequest>, Object> {
}
