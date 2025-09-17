package org.example.domain.interfaces;

import org.example.domain.entities.Delivery;

public interface ShippingPromotion {
    boolean applies(Delivery delivery);
    double applyDiscount(double originalAmount, Delivery delivery);
    String getDescription();
}

