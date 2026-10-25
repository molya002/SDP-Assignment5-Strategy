import java.math.BigDecimal;
import java.util.Objects;

public final class Shipment {
    private final BigDecimal weightKg;
    private final BigDecimal distanceKm;

    public Shipment(BigDecimal weightKg, BigDecimal distanceKm) {
        this.weightKg = requirePositive(weightKg, "Weight");
        this.distanceKm = requirePositive(distanceKm, "Distance");
    }

    private static BigDecimal requirePositive(BigDecimal value, String name) {
        Objects.requireNonNull(value, name + " is required");
        if (value.signum() <= 0) {
            throw new IllegalArgumentException(name + " must be positive");
        }
        return value;
    }

    public BigDecimal getWeightKg() { return weightKg; }
    public BigDecimal getDistanceKm() { return distanceKm; }
}
