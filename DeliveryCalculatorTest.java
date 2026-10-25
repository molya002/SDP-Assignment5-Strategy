import java.math.BigDecimal;

public final class DeliveryCalculatorTest {
    public static void main(String[] args) {
        Shipment shipment = new Shipment(new BigDecimal("2.5"), new BigDecimal("10"));
        DeliveryCalculator calculator = new DeliveryCalculator(new StandardDelivery());
        expect("950.00", calculator.calculateCost(shipment));
        calculator.setStrategy(new ExpressDelivery());
        expect("1775.00", calculator.calculateCost(shipment));
        calculator.setStrategy(new PickupDelivery());
        expect("0.00", calculator.calculateCost(shipment));
        calculator.setStrategy(parcel -> new BigDecimal("750.00"));
        expect("750.00", calculator.calculateCost(shipment));
        calculator.setStrategy(new StandardDelivery());
        expect("600.00", calculator.calculateCost(new Shipment(
                new BigDecimal("0.33333"), new BigDecimal("3.33333"))));
        rejects(IllegalArgumentException.class, () -> new Shipment(BigDecimal.ZERO, BigDecimal.ONE));
        rejects(IllegalArgumentException.class, () -> new Shipment(BigDecimal.ONE, new BigDecimal("-1")));
        rejects(NullPointerException.class, () -> new Shipment(null, BigDecimal.ONE));
        rejects(NullPointerException.class, () -> new DeliveryCalculator(null));
        rejects(NullPointerException.class, () -> calculator.setStrategy(null));
        rejects(NullPointerException.class, () -> calculator.calculateCost(null));
        expect("950.00", calculator.calculateCost(shipment));
        System.out.println("All delivery tests passed.");
    }

    private static void expect(String expected, BigDecimal actual) {
        if (!new BigDecimal(expected).equals(actual)) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
    }

    private static void rejects(Class<? extends Throwable> type, Runnable action) {
        try {
            action.run();
        } catch (Throwable error) {
            if (type.isInstance(error)) { return; }
            throw new AssertionError("Unexpected exception", error);
        }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
}
