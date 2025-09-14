package org.example.logistic.interfaces;

import org.example.logistic.entities.Delivery;

public interface ShippingPromotion {
    boolean applies(Delivery delivery);
    double applyDiscount(double originalAmount, Delivery delivery);
    String getDescription();
}

