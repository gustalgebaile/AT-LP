package org.example.infrastructure.shipping;

import org.example.domain.entities.ShippingType;
import org.example.domain.interfaces.ShippingCalculator;

public class ExpressShipping implements ShippingCalculator {
    private static final double MULTIPLIER = 1.5;
    private static final double FIXED_FEE = 10.0;

    @Override
    public double calculate(double weight) {
        return weight * MULTIPLIER + FIXED_FEE;
    }

    @Override
    public ShippingType getType() {
        return ShippingType.EXPRESS;
    }
}

