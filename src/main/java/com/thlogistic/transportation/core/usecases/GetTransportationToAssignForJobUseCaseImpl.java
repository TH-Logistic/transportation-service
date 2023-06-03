package com.thlogistic.transportation.core.usecases;

import com.thlogistic.transportation.adapters.dtos.BaseTokenRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationToAssignForJobRequest;
import com.thlogistic.transportation.adapters.dtos.GetTransportationToAssignForJobResponse;
import com.thlogistic.transportation.aop.exception.CustomRuntimeException;
import com.thlogistic.transportation.client.google_map.DistanceMatrixDto;
import com.thlogistic.transportation.client.google_map.GoogleMapClient;
import com.thlogistic.transportation.client.route.LocationResponse;
import com.thlogistic.transportation.client.route.RouteClient;
import com.thlogistic.transportation.config.ClientConfig;
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

@Service
@RequiredArgsConstructor
public class GetTransportationToAssignForJobUseCaseImpl implements GetTransportationToAssignForJobUseCase {

    private final TransportationRepository transportationRepository;
    private final GarageRepository garageRepository;
    private final GetGarageUseCase getGarageUseCase;
    private final RouteClient routeClient;
    private final GoogleMapClient googleMapClient;

    @Override
    public List<GetTransportationToAssignForJobResponse> execute(BaseTokenRequest<GetTransportationToAssignForJobRequest> baseTokenRequest) {
        String token = baseTokenRequest.getToken();
        String keyword = baseTokenRequest.getRequestContent().getKeyword(); // Could be transportation's license plate or garage name
        String startingLocationId = baseTokenRequest.getRequestContent().getStartingLocationId(); // Could be transportation's license plate or garage name

        List<GarageEntity> garageEntities;
        if (keyword == null || keyword.isEmpty()) {
            garageEntities = garageRepository.findAll();
        } else {
            garageEntities = garageRepository.findAllByName(keyword); // keyword is garage name
        }
        List<String> garageIds = garageEntities.stream().map(GarageEntity::getId).toList();

        List<TransportationEntity> transportationEntities;
        if (keyword == null || keyword.isEmpty()) {
            transportationEntities = transportationRepository.findAllTransportationToAssignForJob(garageIds);
        } else {
            transportationEntities = transportationRepository.findAllTransportationToAssignForJobWithLicensePlate(keyword, garageIds); // keyword is license plate
        }
        List<GetTransportationToAssignForJobResponse> responseList = new LinkedList<>();

        for (TransportationEntity entity : transportationEntities) {
            GetTransportationToAssignForJobResponse response = new GetTransportationToAssignForJobResponse();

            Garage garage = getGarageUseCase.execute(entity.getGarageId());

            LocationResponse startingLocation;
            DistanceMatrixDto distanceMatrix;

            try {
                startingLocation = routeClient.getLocation(token, startingLocationId).getData();
            } catch (Exception e) {
                throw new CustomRuntimeException("An error occurred when loading location");
            }

            try {
                String origins = String.join(
                        ",",
                        startingLocation.getLatitude().toString(),
                        startingLocation.getLongitude().toString()
                );
                String destinations = String.join(
                        ",",
                        garage.getLatitude().toString(),
                        garage.getLongitude().toString()
                );
                distanceMatrix = googleMapClient.getDistanceBetweenTwoLocation(origins, destinations, ClientConfig.GOOGLE_MAP_API_KEY);
            } catch (Exception e) {
                throw new CustomRuntimeException("An error occurred when calculating distance");
            }

            response.setId(entity.getId());
            response.setLicensePlate(entity.getLicensePlate());
            response.setGarage(garage.getName());
            response.setDistanceToPickUp(distanceMatrix.getDistance());

            responseList.add(response);
        }

        responseList.sort(Comparator.comparingDouble(GetTransportationToAssignForJobResponse::getDistanceToPickUp));
        return responseList;
    }
}
