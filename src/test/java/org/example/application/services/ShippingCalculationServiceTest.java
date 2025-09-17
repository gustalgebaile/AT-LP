package org.example.application.services;

import org.example.domain.entities.Delivery;
import org.example.domain.entities.ShippingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Delivery d = new Delivery("Rua CS", 2.0, ShippingType.STANDARD, "Caio");
        var result = service.calculate(d);
        assertTrue(result.isSuccess());
        assertEquals(2.4, result.getValue());
    }

    @Test
    void shouldApplyFreeShippingPromotion() {
        Delivery d = new Delivery("Rua Vin Disel", 1.0, ShippingType.ECONOMIC, "Michelle Rock");
        var result = service.calculate(d);
        assertTrue(result.isSuccess());
        assertEquals(0.0, result.getValue());
    }
}

