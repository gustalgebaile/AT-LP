package org.example.domain.interfaces;

import org.example.domain.entities.ShippingType;

public interface ShippingCalculator {
    double calculate(double weight);
    ShippingType getType();
}


