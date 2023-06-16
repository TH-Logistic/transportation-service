package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.*;
import com.thlogistic.transportation.aop.exception.CustomRuntimeException;
import com.thlogistic.transportation.client.google_map.DistanceMatrixDto;
import com.thlogistic.transportation.client.google_map.GoogleMapClient;
import com.thlogistic.transportation.client.job.GetJobListDto;
import com.thlogistic.transportation.client.job.GetJobStatisticResponse;
import com.thlogistic.transportation.client.job.JobClient;
import com.thlogistic.transportation.client.job.JobStatisticDto;
import com.thlogistic.transportation.client.route.LocationResponse;
import com.thlogistic.transportation.client.route.RouteClient;
import com.thlogistic.transportation.config.ClientConfig;
import com.thlogistic.transportation.core.entities.DeliveryStatus;
import com.thlogistic.transportation.core.entities.Garage;
import com.thlogistic.transportation.core.ports.GarageRepository;
import com.thlogistic.transportation.core.ports.TransportationRepository;
import com.thlogistic.transportation.entities.GarageEntity;
import com.thlogistic.transportation.entities.TransportationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetEndingGarageToAssignForJobUseCaseImpl implements GetEndingGarageToAssignForJobUseCase {
    private final GarageRepository garageRepository;
    private final RouteClient routeClient;
    private final GoogleMapClient googleMapClient;

    @Override
    public List<GetEndingGarageToAssignForJobResponse> execute(BaseTokenRequest<GetEndingGarageToAssignForJobRequest> baseTokenRequest) {
        String token = baseTokenRequest.getToken();
        String keyword = baseTokenRequest.getRequestContent().getKeyword(); // Could be transportation's license plate or garage name
        String startingLocationId = baseTokenRequest.getRequestContent().getStartingLocationId(); // Could be transportation's license plate or garage name

        List<GarageEntity> garageEntities;
        if (keyword == null || keyword.isEmpty()) {
            garageEntities = garageRepository.findAll();
        } else {
            garageEntities = garageRepository.findAllByName(keyword); // keyword is garage name
        }

        List<GetEndingGarageToAssignForJobResponse> responseList = new LinkedList<>();

        LocationResponse startingLocation;
        try {
            startingLocation = routeClient.getLocation(token, startingLocationId).getData();
        } catch (Exception e) {
            throw new CustomRuntimeException("An error occurred when loading location");
        }
        String origins = String.join(
                ",",
                startingLocation.getLatitude().toString(),
                startingLocation.getLongitude().toString()
        );

        for (GarageEntity entity : garageEntities) {
            GetEndingGarageToAssignForJobResponse response = new GetEndingGarageToAssignForJobResponse();

            DistanceMatrixDto distanceMatrix;

            try {
                String destinations = String.join(
                        ",",
                        entity.getLatitude().toString(),
                        entity.getLongitude().toString()
                );
                distanceMatrix = googleMapClient.getDistanceBetweenTwoLocation(origins, destinations, ClientConfig.GOOGLE_MAP_API_KEY);
            } catch (Exception e) {
                throw new CustomRuntimeException("An error occurred when calculating distance");
            }

            response.setId(entity.getId());
            response.setName(entity.getName());
            response.setAddress(entity.getAddress());
            response.setDistanceToGarage(distanceMatrix.getDistance());

            responseList.add(response);
        }

        responseList.sort(Comparator.comparingDouble(GetEndingGarageToAssignForJobResponse::getDistanceToGarage));
        return responseList;
    }
}
