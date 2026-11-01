package observer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

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
        Integer oldStock = stocks.put(productName, newStock);

        if (oldStock != null && oldStock != newStock) {
            publisher.notifyObservers(
                    new Event(
                            EventType.STOCK_CHANGED,
                            productName,
                            oldStock,
                            newStock
                    )
            );
        }
    }
    public int getStock(String productName) {
        return stocks.getOrDefault(productName, 0);
    }
}
