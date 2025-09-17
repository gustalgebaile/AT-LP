package org.example.application.validators;

import org.example.application.common.OperationResult;
import org.example.domain.entities.Delivery;
import org.example.domain.entities.ShippingType;

public class DeliveryValidator {

    public static OperationResult<Delivery> validateAndCreate(String address, double weight,
                                                              String shippingTypeStr, String recipient) {
        try {
            if (address == null || address.trim().isEmpty()) {
                return OperationResult.failure("Endereço é Necessário");
            }

            if (weight < 0) {
                return OperationResult.failure("O Peso de ser positivo");
            }

            if (weight > 1000) {
                return OperationResult.failure("O peso excede o limite máximo limite de 1000kg");
            }

            if (recipient == null || recipient.trim().isEmpty()) {
                return OperationResult.failure("Destinatário é Necessário");
            }

            ShippingType shippingType;
            try {
                shippingType = ShippingType.fromCode(shippingTypeStr);
            } catch (IllegalArgumentException e) {
                return OperationResult.failure("Tipo de Frete Inválido: " + shippingTypeStr);
            }

            Delivery delivery = new Delivery(address.trim(), weight, shippingType, recipient.trim());
            return OperationResult.success(delivery);

        } catch (Exception e) {
            return OperationResult.failure("Erro de Validação Inesperado: " + e.getMessage());
        }
    }
}


