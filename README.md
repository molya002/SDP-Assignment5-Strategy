# Assignment 5 Strategy pattern

Java console application that calculates a shipment's delivery cost using interchangeable algorithms. All rates are illustrative, in KZT. Requires JDK 11 or newer; no external libraries.

## Run

From the project directory:

```sh
javac -d out *.java
java -cp out Main
java -cp out DeliveryCalculatorTest
```

Expected demo output:

```text
Shipment: 2.5 kg, 10 km. Prices are in KZT.
Standard: 950.00
Express: 1775.00
Pickup: 0.00
New flat-rate strategy: 750.00
```

## Pattern components

| Component | Class | Responsibility |
| --- | --- | --- |
| Strategy | DeliveryStrategy | Declares calculateCost(Shipment). |
| Concrete strategy | StandardDelivery | 500 + weightKg * 100 + distanceKm * 20. |
| Concrete strategy | ExpressDelivery | 1000 + weightKg * 150 + distanceKm * 40. |
| Concrete strategy | PickupDelivery | Free pickup, returning 0.00. |
| Context | DeliveryCalculator | Stores a strategy, delegates calculation and supports setStrategy. |
| Client | Main | Constructs and changes strategies on the same calculator at runtime. |
| Input model | Shipment | Stores validated positive weight and distance. |

The context never checks a concrete strategy type. Java dispatches calculateCost to the current implementation through the interface. Validation checks in Shipment check input values, not strategy types.

## Clean Code justification

1. **Meaningful names.** DeliveryCalculator, calculateCost, weightKg and RATE_PER_KM state their purpose and units.
2. **Single responsibility.** Each strategy implements one pricing algorithm. Shipment validates input; Main configures the demonstration; the context delegates execution.
3. **Polymorphism instead of branching.** There is no if, switch or instanceof in DeliveryCalculator for choosing algorithms. The client supplies an implementation.
4. **Depend on an abstraction.** DeliveryCalculator only references DeliveryStrategy, so it does not know pricing formulas or concrete strategy classes.
5. **Open for extension.** Main supplies a fourth flat-rate strategy as a lambda without changing DeliveryCalculator or the three existing strategies. A separate class implementing DeliveryStrategy can be added the same way.
6. **Small focused methods and encapsulation.** Pricing methods calculate one result. Shipment has private final fields; strategy replacement is controlled through setStrategy.
7. **Named constants.** Pricing rates are named rather than unexplained numbers in formulas. BigDecimal preserves decimal precision, and paid strategies round the final total to two decimal places.

## Verification

The executable tests check the three formulas, switching on one context, an additional strategy, rounding, invalid inputs and null rejection. They also check that rejecting a null strategy leaves the previous strategy usable.

## Defense preparation

- Strategy is a behavioral pattern that puts interchangeable algorithms behind one interface.
- The context holds an interface reference; the client chooses the concrete object.
- Calling setStrategy changes behavior at runtime without replacing the context.
- Standard and express delivery use different rates; pickup has no delivery fee.
- Adding another algorithm requires a new implementation and client configuration. Existing algorithms and context stay unchanged.
- An interface defines the common contract. Composition lets the context change its algorithm; a fixed inheritance hierarchy would not provide this instance-level replacement.
- This small example adds several classes, which is a tradeoff. Strategy is useful when algorithms vary independently and must be exchanged at runtime.

## Объяснение для защиты

В проекте Strategy — это DeliveryStrategy. Три реализации считают цену обычной доставки, экспресс-доставки и самовывоза. DeliveryCalculator — Context: он хранит ссылку на интерфейс и вызывает calculateCost. Main — Client: выбирает реализации и меняет их через setStrategy у одного объекта во время выполнения.

Для посылки 2.5 кг на 10 км обычная доставка стоит 950 KZT, экспресс — 1775 KZT, самовывоз — 0 KZT. Context не содержит проверки типа стратегии. Четвёртый алгоритм с фиксированной ценой 750 KZT добавлен лямбдой в Main, чтобы показать расширяемость.

Перед сдачей необходимо проверить, что тема доставки не использовалась в лекции и не занята другим студентом группы.
