package org.example.calculators;

import org.example.domain.interfaces.ShippingCalculator;
import org.example.infrastructure.shipping.StandardShipping;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardShippingTest {
    @Test
    void calculateStandard() {
        ShippingCalculator calc = new StandardShipping();
        assertEquals(1.2 * 2.0, calc.calculate(2.0));
    }
}