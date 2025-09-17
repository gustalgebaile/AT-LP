package org.example.application.services;

import org.example.domain.entities.Delivery;
import org.example.domain.entities.ShippingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeliveryServiceTest {

    private ShippingCalculationService shippingService;
    private LabelService labelService;
    private PromotionManager promotions;
    private DeliveryService deliveryService;

    @BeforeEach
    void setup() {
        promotions = new PromotionManager();
        shippingService = new ShippingCalculationService(promotions);
        labelService = new LabelService(shippingService);
        deliveryService = new DeliveryService(shippingService, labelService, promotions);
    }

    @Test
    void shouldProcessCompleteDelivery() {
        Delivery d = new Delivery("Rua Groto", 5.0, ShippingType.EXPRESS, "Helena");
        var result = deliveryService.processCompleteDelivery(d);

        assertTrue(result.isSuccess());
        String output = result.getValue();
        assertTrue(output.contains("Destinatário: Helen"));
        assertTrue(output.contains("Tipo de Frete: EXP"));
        assertTrue(output.contains("Custo do Frete:"));
    }
}

