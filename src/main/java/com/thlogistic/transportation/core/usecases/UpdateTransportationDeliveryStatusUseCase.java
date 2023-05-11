package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.UpdateTransportationDeliveryStatusRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public interface UpdateTransportationDeliveryStatusUseCase extends BaseUseCase<Pair<String, UpdateTransportationDeliveryStatusRequest>, Boolean> {
}
