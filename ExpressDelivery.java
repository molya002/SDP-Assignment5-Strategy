import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ExpressDelivery implements DeliveryStrategy {
    private static final BigDecimal BASE_FEE = new BigDecimal("1000");
    private static final BigDecimal RATE_PER_KG = new BigDecimal("150");
    private static final BigDecimal RATE_PER_KM = new BigDecimal("40");

    @Override
    public BigDecimal calculateCost(Shipment shipment) {
        return BASE_FEE.add(shipment.getWeightKg().multiply(RATE_PER_KG))
                .add(shipment.getDistanceKm().multiply(RATE_PER_KM))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
