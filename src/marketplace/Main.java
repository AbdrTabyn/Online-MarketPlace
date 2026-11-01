package marketplace;
import java.util.List;
import visitor.TaxVisitor;
import visitor.ShippingVisitor;
import visitor.ProductFilterVisitor;
import visitor.XmlExportVisitor;
import observer.*;
import listener.*;

public class Main {
    public static void main(String[] args) {
        PhysicalProduct phone = new PhysicalProduct("Phone", 1000, 0.5);
        DigitalProduct game = new DigitalProduct("Game", 50);
        ServiceProduct installation = new ServiceProduct("Installation", 30);
        SubscriptionProduct premium = new SubscriptionProduct("Premium", 10, 12);

        Subcategory products = new Subcategory(
                "Products",
                List.of(phone, game, installation, premium)
        );

        Category electronics = new Category(
                "Electronics",
                List.of(products)
        );

        Catalog catalog = new Catalog(
                "Online Marketplace",
                List.of(electronics)
        );

        System.out.println(catalog.getName());
        System.out.println(
                catalog.getCategories().get(0).getName()
        );

        TaxVisitor taxVisitor = new TaxVisitor();
        System.out.println("Total tax: " + catalog.accept(taxVisitor));

        ShippingVisitor shippingVisitor = new ShippingVisitor();
        System.out.println("Total shipping: " + catalog.accept(shippingVisitor)
        );

        ProductFilterVisitor filterVisitor = new ProductFilterVisitor(100);
        System.out.println("Products under $100: " + catalog.accept(filterVisitor)
        );

        XmlExportVisitor xmlVisitor = new XmlExportVisitor();
        System.out.println("XML Export:");
        System.out.println(catalog.accept(xmlVisitor));

        System.out.println("--- Observer ---");

        EventPublisher publisher = new EventPublisher();

        PriceService priceService = new PriceService(publisher);
        StockService stockService = new StockService(publisher);

        WishlistNotifier wishlist = new WishlistNotifier();
        PriceDropAlert priceAlert = new PriceDropAlert();

        publisher.subscribe(EventType.PRICE_CHANGED, wishlist);
        publisher.subscribe(EventType.PRICE_CHANGED, priceAlert);


        AnalyticsListener analytics = new AnalyticsListener();
        CacheListener cache = new CacheListener();
        MarketplaceAggregator aggregator =
                new MarketplaceAggregator(publisher);

        cache.addToCache("Phone");

        publisher.subscribe(EventType.PRICE_CHANGED, analytics);
        publisher.subscribe(EventType.STOCK_CHANGED, analytics);

        publisher.subscribe(EventType.PRICE_CHANGED, cache);
        publisher.subscribe(EventType.STOCK_CHANGED, cache);

        publisher.subscribe(EventType.PRICE_CHANGED, aggregator);
        publisher.subscribe(EventType.STOCK_CHANGED, aggregator);

        publisher.subscribe(EventType.MARKETPLACE_SUMMARY,
                event -> System.out.println(
                        "Summary: total changes = " + (int) event.getNewValue()
                ));


        priceService.setPrice("Phone", 1000);
        PriceChangeHandler priceHandler = new PriceChangeHandler(catalog);

        publisher.subscribe(EventType.PRICE_CHANGED, priceHandler);
        priceService.setPrice("Phone", 800);

        stockService.setStock("Phone", 10);
        stockService.setStock("Phone", 5);
    }
}
