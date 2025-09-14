package org.example.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShippingCalculationServiceTest {

    private PromotionManager promotions;
    private ShippingCalculationService service;

    @BeforeEach
    void setup() {
        promotions = new PromotionManager();
        service = new ShippingCalculationService(promotions);
    }

    @Test
    void shouldCalculateStandardShipping() {
        Delivery d = new Delivery("Street D", 2.0, ShippingType.STANDARD, "Eve");
        var result = service.calculate(d);
        assertTrue(result.isSuccess());
        assertEquals(2.4, result.getValue());
    }

    @Test
    void shouldApplyFreeShippingPromotion() {
        Delivery d = new Delivery("Street E", 1.0, ShippingType.ECONOMIC, "Eve");
        var result = service.calculate(d);
        assertTrue(result.isSuccess());
        assertEquals(0.0, result.getValue());
    }
}

