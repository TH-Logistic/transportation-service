package com.thlogistic.transportation.client.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetJobStatisticResponse {
    JobStatisticDto statistic;
    List<GetJobListDto> jobs;
}
