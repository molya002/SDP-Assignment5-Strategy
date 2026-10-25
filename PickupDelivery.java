import java.math.BigDecimal;

public final class PickupDelivery implements DeliveryStrategy {
    @Override
    public BigDecimal calculateCost(Shipment shipment) {
        return new BigDecimal("0.00");
    }
}
