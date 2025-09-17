package org.example.domain.exceptions;

public class ShippingCalculationException extends DeliveryException {
    public ShippingCalculationException(String message) {
        super("Erro de cálculo do Frete: " + message);
    }
}

