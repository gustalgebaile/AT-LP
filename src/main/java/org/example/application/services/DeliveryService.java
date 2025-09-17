package org.example.application.services;

import org.example.application.common.OperationResult;
import org.example.domain.entities.Delivery;

import java.util.List;

public class DeliveryService {
    private final ShippingCalculationService shippingService;
    private final LabelService labelService;
    private final PromotionManager promotionManager;

    public DeliveryService(ShippingCalculationService shippingService,
                           LabelService labelService,
                           PromotionManager promotionManager) {
        this.shippingService = shippingService;
        this.labelService = labelService;
        this.promotionManager = promotionManager;
    }

    public OperationResult<String> processCompleteDelivery(Delivery delivery) {
        OperationResult<Double> shippingResult = shippingService.calculate(delivery);
        if (!shippingResult.isSuccess()) {
            return OperationResult.failure(shippingResult.getError());
        }

        List<String> promotions = promotionManager.getApplicablePromotions(delivery);

        StringBuilder result = new StringBuilder();
        result.append("=== ENTREGA PROCESSANDO ===\n");
        result.append(String.format("Destinatário: %s\n", delivery.getRecipient()));
        result.append(String.format("Endereço: %s\n", delivery.getAddress()));
        result.append(String.format("Peso: %.2fkg\n", delivery.getWeight()));
        result.append(String.format("Tipo de Frete: %s\n", delivery.getShippingType().getCode()));
        result.append(String.format("Custo do Frete: $%.2f\n", shippingResult.getValue()));

        if (!promotions.isEmpty()) {
            result.append("Promoção Aplicada:\n");
            promotions.forEach(promotion -> result.append("- ").append(promotion).append("\n"));
        }

        return OperationResult.success(result.toString());
    }
}

