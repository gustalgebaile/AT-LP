package org.example.application.services;

import org.example.application.common.OperationResult;
import org.example.domain.entities.Delivery;

public class LabelService {
    private final ShippingCalculationService shippingService;

    public LabelService(ShippingCalculationService shippingService) {
        this.shippingService = shippingService;
    }

    public OperationResult<String> generateBasicLabel(Delivery delivery) {
        OperationResult<Double> shippingResult = shippingService.calculate(delivery);
        if (!shippingResult.isSuccess()) {
            return OperationResult.failure("Erro de Cálculo do Frete: " + shippingResult.getError());
        }

        String label = String.format(
                "Destinatário: %s%nEndereço: %s%nCusto do Frete: $%.2f",
                delivery.getRecipient(),
                delivery.getAddress(),
                shippingResult.getValue()
        );

        return OperationResult.success(label);
    }

    public OperationResult<String> generateOrderSummary(Delivery delivery) {
        OperationResult<Double> shippingResult = shippingService.calculate(delivery);
        if (!shippingResult.isSuccess()) {
            return OperationResult.failure("Erro de Cálculo do Frete: " + shippingResult.getError());
        }

        String summary = String.format(
                "Pedido para %s com %s frete de $%.2f",
                delivery.getRecipient(),
                delivery.getShippingType().getCode(),
                shippingResult.getValue()
        );

        return OperationResult.success(summary);
    }
}


