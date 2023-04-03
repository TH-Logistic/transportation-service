package com.thlogistic.transportation.transportation.adapters.repositories;

import com.thlogistic.transportation.transportation.core.ports.TransportationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransportationRepositoryImpl implements TransportationRepository {
    private final MongoTransportationRepository repository;


}
