package org.example.domain;

import org.example.domain.entities.ShippingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShippingTypeTest {

    @Test
    void shouldReturnEnumFromValidCode() {
        assertEquals(ShippingType.ECONOMIC, ShippingType.fromCode("ECO"));
    }

    @Test
    void shouldThrowForInvalidCode() {
        assertThrows(IllegalArgumentException.class, () ->
                ShippingType.fromCode("XYZ")
        );
    }
}

