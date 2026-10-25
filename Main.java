import java.math.BigDecimal;

public final class Main {
    public static void main(String[] args) {
        Shipment shipment = new Shipment(new BigDecimal("2.5"), new BigDecimal("10"));
        DeliveryCalculator calculator = new DeliveryCalculator(new StandardDelivery());

        System.out.println("Shipment: 2.5 kg, 10 km. Prices are in KZT.");
        System.out.println("Standard: " + calculator.calculateCost(shipment));

        calculator.setStrategy(new ExpressDelivery());
        System.out.println("Express: " + calculator.calculateCost(shipment));

        calculator.setStrategy(new PickupDelivery());
        System.out.println("Pickup: " + calculator.calculateCost(shipment));

        // A new algorithm can be supplied without editing the context or existing strategies.
        calculator.setStrategy(parcel -> new BigDecimal("750.00"));
        System.out.println("New flat-rate strategy: " + calculator.calculateCost(shipment));
    }
}
