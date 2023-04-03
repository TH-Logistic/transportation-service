package com.thlogistic.transportation.transportation.adapters.repositories;

import lombok.Data;

@Data
public class BasePagingQueryResult<T> {
    Long total;
    Integer totalPage;
    T data;
}
