package org.example.infrastructure.frete.calculadoras;

import org.example.logistica.entities.ShippingType;
import org.example.logistica.interfaces.ShippingCalculator;

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

