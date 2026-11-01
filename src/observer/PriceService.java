package observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PriceService {
    private final EventPublisher publisher;
    private final Map<String, Double> prices = new ConcurrentHashMap<>();

    public PriceService(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void setPrice(String productName, double newPrice) {
        if (newPrice < 0 || !Double.isFinite(newPrice)) {
            throw new IllegalArgumentException("Invalid price");
        }
        final Double[] previous = new Double[1];
        prices.compute(productName, (name, oldPrice) -> {
            previous[0] = oldPrice;
            return newPrice;
        });

        if (previous[0] != null && previous[0] != newPrice) {
            publisher.notifyObservers(
                    new Event(
                            EventType.PRICE_CHANGED,
                            productName,
                            previous[0],
                            newPrice
                    )
            );
        }
    }
    public double getPrice(String productName) {
        return prices.getOrDefault(productName, 0.0);
    }

    public boolean hasPrice(String productName) {
        return prices.containsKey(productName);
    }
}
