package org.example.application.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryValidatorTest {

    @Test
    void shouldValidateAndCreateDelivery() {
        var result = DeliveryValidator.validateAndCreate("Rua Praia de Botadogo", 4.0, "STD", "Seu Paulo");
        assertTrue(result.isSuccess());
        assertEquals("Seu Paulo", result.getValue().getRecipient());
    }

    @Test
    void shouldFailOnInvalidType() {
        var result = DeliveryValidator.validateAndCreate("Rua Oliveira Rocha", 4.0, "XYZ", "Seu Antonio");
        assertFalse(result.isSuccess());
        assertEquals("Tipo de Frete Inválido: XYZ", result.getError());
    }
}
