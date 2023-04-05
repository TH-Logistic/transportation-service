package com.thlogistic.transportation.core.entities;


import com.thlogistic.transportation.aop.exception.InvalidDeliveryStatusException;

public enum DeliveryStatus {
    IDLE(1),
    DELIVERY(2);

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
