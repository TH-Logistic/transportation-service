package com.thlogistic.transportation.adapters.dtos;

import com.thlogistic.transportation.client.job.GetJobListDto;
import com.thlogistic.transportation.client.job.JobStatisticDto;
import com.thlogistic.transportation.client.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDriverDetailResponse {
    UserInfoDto driver;
    JobStatisticDto statistic;
    List<GetJobListDto> jobs;
}
