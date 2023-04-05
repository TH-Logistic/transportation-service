package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.UpdateTransportationRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public interface UpdateTransportationUseCase extends BaseUseCase<Pair<String, UpdateTransportationRequest>, Object> {
}
