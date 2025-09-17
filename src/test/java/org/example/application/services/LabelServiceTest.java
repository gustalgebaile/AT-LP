package org.example.application.services;

import org.example.application.common.OperationResult;
import org.example.domain.entities.Delivery;
import org.example.domain.entities.ShippingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LabelServiceTest {

    private ShippingCalculationService shippingService;
    private LabelService labelService;

    @BeforeEach
    void setup() {
        shippingService = mock(ShippingCalculationService.class);
        labelService = new LabelService(shippingService);
    }

    @Test
    void shouldGenerateBasicLabel() {
        Delivery d = new Delivery("Rua GB", 3.0, ShippingType.EXPRESS, "Gabriel");
        when(shippingService.calculate(d)).thenReturn(OperationResult.success(15.0));

        var label = labelService.generateBasicLabel(d);
        assertTrue(label.isSuccess());
        assertTrue(label.getValue().contains("Destinatário: Gabriel"));
        assertTrue(label.getValue().contains("Endereço: Rua GB"));
    }

    @Test
    void shouldReturnErrorWhenCalculationFails() {
        Delivery d = new Delivery("Street G", 3.0, ShippingType.EXPRESS, "Gina");
        when(shippingService.calculate(d)).thenReturn(OperationResult.failure("Erro"));

        var label = labelService.generateBasicLabel(d);
        assertFalse(label.isSuccess());
        assertEquals("Erro de Cálculo do Frete: Erro", label.getError());
    }
}
