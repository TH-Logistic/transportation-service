package com.thlogistic.transportation.adapters.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListTransportationPagingRequest extends BasePagingRequest {
    String keyword;
    Integer deliveryStatus;
}
