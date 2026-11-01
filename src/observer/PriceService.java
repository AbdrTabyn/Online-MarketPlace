package observer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class PriceService {
    private final EventPublisher publisher;
    private final Map<String, Double> prices = new ConcurrentHashMap<>();

    public PriceService(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void setPrice(String productName, double newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        Double oldPrice = prices.put(productName, newPrice);

        if (oldPrice != null && oldPrice != newPrice) {
            publisher.notifyObservers(
                    new Event(
                            EventType.PRICE_CHANGED,
                            productName,
                            oldPrice,
                            newPrice
                    )
            );
        }
    }
    public double getPrice(String productName) {
        return prices.getOrDefault(productName, 0.0);
    }
}
