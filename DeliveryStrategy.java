import java.math.BigDecimal;

public interface DeliveryStrategy {
    BigDecimal calculateCost(Shipment shipment);
}
