package org.example.infrastructure.promotions;

import org.example.domain.entities.Delivery;
import org.example.domain.entities.ShippingType;
import org.example.domain.interfaces.ShippingPromotion;

public class FreeShippingPromotion implements ShippingPromotion {
    @Override
    public boolean applies(Delivery delivery) {
        return delivery.getShippingType() == ShippingType.ECONOMIC && delivery.getWeight() < 2;
    }

    @Override
    public double applyDiscount(double originalAmount, Delivery delivery) {
        return 0.0;
    }

    @Override
    public String getDescription() {
        return "Frete grátis para fretes econômicos em até 2kg";
    }
}

