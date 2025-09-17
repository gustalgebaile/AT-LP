package org.example.calculators;

import org.example.domain.interfaces.ShippingCalculator;
import org.example.infrastructure.shipping.EconomicShipping;
import org.example.infrastructure.shipping.ExpressShipping;
import org.example.infrastructure.shipping.StandardShipping;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressShippingTest {
    @Test
    void calculateExpress() {
        ShippingCalculator calc = new ExpressShipping();
        assertEquals(1.5 * 2 + 10, calc.calculate(2.0));
    }
}
