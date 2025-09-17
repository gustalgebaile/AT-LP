package org.example.application.services;

import org.example.application.common.OperationResult;
import org.example.infrastructure.shipping.EconomicShipping;
import org.example.infrastructure.shipping.ExpressShipping;
import org.example.infrastructure.shipping.StandardShipping;
import org.example.domain.entities.Delivery;
import org.example.domain.entities.ShippingType;
import org.example.domain.interfaces.ShippingCalculator;

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
                return OperationResult.failure("Tipo de Frete não Suportado: " + delivery.getShippingType());
            }

            double baseAmount = calculator.calculate(delivery.getWeight());
            double finalAmount = promotionManager.calculateWithPromotions(baseAmount, delivery);

            return OperationResult.success(Math.max(0, finalAmount));
        } catch (Exception e) {
            return OperationResult.failure("Erro de Cálculo: " + e.getMessage());
        }
    }

    public boolean isFreeShipping(Delivery delivery) {
        return promotionManager.getApplicablePromotions(delivery)
                .stream()
                .anyMatch(desc -> desc.toLowerCase().contains("gratis"));
    }

    private Map<ShippingType, ShippingCalculator> initializeCalculators() {
        return Map.of(
                ShippingType.EXPRESS, new ExpressShipping(),
                ShippingType.STANDARD, new StandardShipping(),
                ShippingType.ECONOMIC, new EconomicShipping()
        );
    }
}
