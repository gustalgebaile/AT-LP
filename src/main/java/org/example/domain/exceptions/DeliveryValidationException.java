package org.example.domain.exceptions;

public class DeliveryValidationException extends DeliveryException {
    public DeliveryValidationException(String message) {
        super("Erro de Validação: " + message);
    }
}

