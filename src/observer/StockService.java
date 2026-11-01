package observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockService {
    private final EventPublisher publisher;
    private final Map<String, Integer> stocks = new ConcurrentHashMap<>();

    public StockService(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void setStock(String productName, int newStock) {
        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        final Integer[] previous = new Integer[1];
        stocks.compute(productName, (name, oldStock) -> {
            previous[0] = oldStock;
            return newStock;
        });

        if (previous[0] != null && previous[0] != newStock) {
            publisher.notifyObservers(
                    new Event(
                            EventType.STOCK_CHANGED,
                            productName,
                            previous[0],
                            newStock
                    )
            );
        }
    }
    public int getStock(String productName) {
        return stocks.getOrDefault(productName, 0);
    }
}
