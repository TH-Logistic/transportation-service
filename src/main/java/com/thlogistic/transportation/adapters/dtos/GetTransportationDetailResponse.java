package com.thlogistic.transportation.adapters.dtos;

import com.thlogistic.transportation.client.job.GetJobListDto;
import com.thlogistic.transportation.client.job.JobStatisticDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTransportationDetailResponse {
    GetTransportationResponse product;
    JobStatisticDto statistic;
    List<GetJobListDto> jobs;
}
