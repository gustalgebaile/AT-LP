package org.example.calculators;

import org.example.domain.interfaces.ShippingCalculator;
import org.example.infrastructure.shipping.EconomicShipping;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EconomicShippingTest {
    @Test
    void calculateEconomic() {
        ShippingCalculator calc = new EconomicShipping();
        assertEquals(1.1 * 3.0 - 5.0, calc.calculate(3.0));
    }
}
