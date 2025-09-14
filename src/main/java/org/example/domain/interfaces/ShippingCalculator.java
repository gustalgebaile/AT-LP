package org.example.logistic.interfaces;

import org.example.logistic.entities.ShippingType;

public interface ShippingCalculator {
    double calculate(double weight);
    ShippingType getType();
}


