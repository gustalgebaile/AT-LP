package org.example.infrastructure.shipping;

import org.example.domain.entities.ShippingType;
import org.example.domain.interfaces.ShippingCalculator;

public class StandardShipping implements ShippingCalculator {
    private static final double MULTIPLIER = 1.2;

    @Override
    public double calculate(double weight) {
        return weight * MULTIPLIER;
    }

    @Override
    public ShippingType getType() {
        return ShippingType.STANDARD;
    }
}

