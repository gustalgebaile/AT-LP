package org.example.domain;

import org.example.domain.entities.Delivery;
import org.example.domain.entities.ShippingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryTest {

    @Test
    void shouldCreateDeliveryWhenValid() {
        Delivery d = new Delivery("Rua Estrada do Grotão", 2.0, ShippingType.STANDARD, "Flavio");
        assertEquals("Rua Estrada do Grotão", d.getAddress());
        assertEquals(2.0, d.getWeight());
        assertEquals(ShippingType.STANDARD, d.getShippingType());
        assertEquals("Flavio", d.getRecipient());
    }

    @Test
    void shouldThrowWhenAddressEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Delivery("", 1.0, ShippingType.EXPRESS, "Cocão")
        );
    }

    @Test
    void shouldThrowWhenWeightNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Delivery("Av Roberto Dinamite", -1.0, ShippingType.EXPRESS, "Bob")
        );
    }

    @Test
    void shouldThrowWhenTypeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Delivery("Rua Edmundo", 1.0, null, "Léo Jardim")
        );
    }
}

