package com.thlogistic.transportation.adapters.repositories;

import com.thlogistic.transportation.entities.GarageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGarageRepository extends MongoRepository<GarageEntity, String> {

}
