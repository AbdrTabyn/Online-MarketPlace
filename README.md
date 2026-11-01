# Online Marketplace — Variant 4

## Description

A Java project demonstrating the **Visitor** and **Observer** design patterns in an online marketplace.

## Technologies

- Java
- JUnit 5
- ExecutorService
- ConcurrentHashMap
- CopyOnWriteArrayList

## Visitor Pattern

The marketplace catalog has a hierarchical structure:

`Catalog → Category → Subcategory → Products`

Product types:
- PhysicalProduct
- DigitalProduct
- ServiceProduct
- SubscriptionProduct
- GiftCardProduct

Implemented visitors:
- TaxVisitor — calculates taxes.
- ShippingVisitor — calculates shipping costs.
- ProductFilterVisitor — filters products by price.
- XmlExportVisitor — exports the catalog to XML.
- ProductCountVisitor — counts products.

Each catalog element implements `accept()`. Visitors perform operations without putting those operations inside the element classes.

## Observer Pattern

The system processes price and stock changes using typed events.

Main components:
- EventPublisher — manages subscriptions.
- PriceService — manages product prices.
- StockService — manages stock quantities.
- AnalyticsListener — counts events.
- MarketplaceAggregator — publishes summary events.
- PriceChangeHandler — runs a Visitor after a price change.

Listeners can subscribe and unsubscribe at runtime.

## Concurrency

The application uses `ExecutorService` with four worker threads.

Each thread generates 1,000 stock-change events.

Expected events: **4,000**

Actual events: **4,000**

Thread safety is provided by concurrent collections and atomic counters.

## Testing

The project contains **15 JUnit 5 tests** in four classes:

- VisitorTest
- ObserverTest
- IntegrationTest
- ConcurrencyTest

Tests cover design patterns, event handling, integration, and concurrency.

## Extensibility and Trade-offs

Adding `ProductCountVisitor` demonstrates extending operations without changing existing element classes.

Adding `GiftCardProduct` demonstrates extending the catalog with a new product type, which requires updating the Visitor interface and its implementations.

Visitor makes new operations easier to add but requires changes when adding element types.

Observer reduces coupling between services and listeners but requires careful handling of concurrent events.

## Running

1. Open the project in IntelliJ IDEA.
2. Run `marketplace.Main`.
3. Run the `test` directory to execute JUnit tests.

UML diagrams and detailed design explanations are provided separately in the project report.