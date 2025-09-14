package org.example.application.services;

import org.example.application.common.OperationResult;
import org.example.logistica.entities.Delivery;
import org.example.logistica.entities.ShippingType;
import org.example.logistica.interfaces.ShippingCalculator;

import java.util.Map;

public class ShippingCalculationService {
    private final Map<ShippingType, ShippingCalculator> calculators;
    private final PromotionManager promotionManager;

    public ShippingCalculationService(PromotionManager promotionManager) {
        this.promotionManager = promotionManager;
        this.calculators = initializeCalculators();
    }

    public OperationResult<Double> calculate(Delivery delivery) {
        try {
            ShippingCalculator calculator = calculators.get(delivery.getShippingType());
            if (calculator == null) {
                return OperationResult.failure("Shipping type not supported: " + delivery.getShippingType());
            }

            double baseAmount = calculator.calculate(delivery.getWeight());
            double finalAmount = promotionManager.calculateWithPromotions(baseAmount, delivery);

            return OperationResult.success(Math.max(0, finalAmount));
        } catch (Exception e) {
            return OperationResult.failure("Calculation error: " + e.getMessage());
        }
    }

    public boolean isFreeShipping(Delivery delivery) {
        return promotionManager.getApplicablePromotions(delivery)
                .stream()
                .anyMatch(desc -> desc.toLowerCase().contains("free"));
    }

    private Map<ShippingType, ShippingCalculator> initializeCalculators() {
        return Map.of(
                ShippingType.EXPRESS, new ExpressShipping(),
                ShippingType.STANDARD, new StandardShipping(),
                ShippingType.ECONOMIC, new EconomicShipping()
        );
    }
}
