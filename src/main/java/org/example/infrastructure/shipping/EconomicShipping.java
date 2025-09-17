package org.example.infrastructure.shipping;

import org.example.domain.entities.ShippingType;
import org.example.domain.interfaces.ShippingCalculator;

public class EconomicShipping implements ShippingCalculator {
    private static final double MULTIPLIER = 1.1;
    private static final double DISCOUNT = 5.0;

    @Override
    public double calculate(double weight) {
        return weight * MULTIPLIER - DISCOUNT;
    }

    @Override
    public ShippingType getType() {
        return ShippingType.ECONOMIC;
    }
}

