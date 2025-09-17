package org.example.infrastructure.promotions;

import org.example.domain.entities.Delivery;
import org.example.domain.interfaces.ShippingPromotion;

public class HeavyWeightPromotion implements ShippingPromotion {
    private static final double MINIMUM_WEIGHT = 10.0;
    private static final double DISCOUNT_PERCENTAGE = 0.10;

    @Override
    public boolean applies(Delivery delivery) {
        return delivery.getWeight() > MINIMUM_WEIGHT;
    }

    @Override
    public double applyDiscount(double originalAmount, Delivery delivery) {
        return originalAmount * (1 - DISCOUNT_PERCENTAGE);
    }

    @Override
    public String getDescription() {
        return "10% de desconto para entregas acima de" + MINIMUM_WEIGHT + "kg";
    }
}

