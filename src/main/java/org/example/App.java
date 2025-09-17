import org.example.application.common.OperationResult;
import org.example.application.services.DeliveryService;
import org.example.application.services.LabelService;
import org.example.application.services.PromotionManager;
import org.example.application.services.ShippingCalculationService;
import org.example.application.validators.DeliveryValidator;
import org.example.domain.entities.Delivery;

public static void main(String[] args) {
    PromotionManager promotionManager = new PromotionManager();
    ShippingCalculationService shippingService = new ShippingCalculationService(promotionManager);
    LabelService labelService = new LabelService(shippingService);
    DeliveryService deliveryService = new DeliveryService(shippingService, labelService, promotionManager);

    OperationResult<Delivery> deliveryResult = DeliveryValidator.validateAndCreate(
            "Rua da Flor 123",
            1.5,
            "ECO",
            "Pedro"
    );

    if (!deliveryResult.isSuccess()) {
        System.out.println("Erro: " + deliveryResult.getError());
        return;
    }

    Delivery delivery = deliveryResult.getValue();

    OperationResult<String> label = deliveryService.processCompleteDelivery(delivery);
    if (label.isSuccess()) {
        System.out.println(label.getValue());
    } else {
        System.out.println("Erro de geração do comprovante: " + label.getError());
    }
}

