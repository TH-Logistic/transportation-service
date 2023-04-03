package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.entities.TransportationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTransportationRepository extends MongoRepository<TransportationEntity, String> {

}
