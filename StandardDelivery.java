import java.math.BigDecimal;
import java.math.RoundingMode;

public final class StandardDelivery implements DeliveryStrategy {
    private static final BigDecimal BASE_FEE = new BigDecimal("500");
    private static final BigDecimal RATE_PER_KG = new BigDecimal("100");
    private static final BigDecimal RATE_PER_KM = new BigDecimal("20");

    @Override
    public BigDecimal calculateCost(Shipment shipment) {
        return BASE_FEE.add(shipment.getWeightKg().multiply(RATE_PER_KG))
                .add(shipment.getDistanceKm().multiply(RATE_PER_KM))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
