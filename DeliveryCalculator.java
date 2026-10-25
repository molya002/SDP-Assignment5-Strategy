import java.math.BigDecimal;
import java.util.Objects;

public final class DeliveryCalculator {
    private DeliveryStrategy strategy;

    public DeliveryCalculator(DeliveryStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(DeliveryStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
    }

    public BigDecimal calculateCost(Shipment shipment) {
        return strategy.calculateCost(Objects.requireNonNull(shipment, "Shipment is required"));
    }
}
