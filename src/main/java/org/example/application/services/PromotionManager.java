package org.example.application.services;

import org.example.infrastructure.promotions.FreeShippingPromotion;
import org.example.infrastructure.promotions.HeavyWeightPromotion;
import org.example.domain.entities.Delivery;
import org.example.domain.interfaces.ShippingPromotion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionManager {
    private final List<ShippingPromotion> promotions;

    public PromotionManager() {
        this.promotions = new ArrayList<>();
        promotions.add(new FreeShippingPromotion());
        promotions.add(new HeavyWeightPromotion());
    }

    public void addPromotion(ShippingPromotion promotion) {
        promotions.add(promotion);
    }

    public double calculateWithPromotions(double originalAmount, Delivery delivery) {
        return promotions.stream()
                .filter(promotion -> promotion.applies(delivery))
                .findFirst()
                .map(promotion -> promotion.applyDiscount(originalAmount, delivery))
                .orElse(originalAmount);
    }

    public List<String> getApplicablePromotions(Delivery delivery) {
        return promotions.stream()
                .filter(promotion -> promotion.applies(delivery))
                .map(ShippingPromotion::getDescription)
                .collect(Collectors.toList());
    }
}


