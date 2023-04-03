package com.thlogistic.transportation.transportation.core.entities;


import com.thlogistic.transportation.transportation.aop.exception.InvalidDeliveryStatusException;

public enum DeliveryStatus {
    ON_ROAD(1),
    AT_GARAGE(2);

    public final Integer status;

    DeliveryStatus(Integer status) {
        this.status = status;
    }

    public static DeliveryStatus fromInt(Integer value) {
        for (DeliveryStatus type : values()) {
            if (type.status.equals(value)) {
                return type;
            }
        }
        throw new InvalidDeliveryStatusException("Delivery status not found");
    }

}
