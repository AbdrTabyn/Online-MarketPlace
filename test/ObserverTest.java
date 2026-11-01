import observer.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {
    @Test
    void testPriceChanged() {
        EventPublisher publisher = new EventPublisher();
        PriceService service = new PriceService(publisher);
        AtomicInteger count = new AtomicInteger();

        publisher.subscribe(EventType.PRICE_CHANGED,
                event -> count.incrementAndGet());

        service.setPrice("Phone", 1000);
        service.setPrice("Phone", 800);

        assertEquals(1, count.get());
    }

    @Test
    void testStockChanged() {
        EventPublisher publisher = new EventPublisher();
        StockService service = new StockService(publisher);
        AtomicInteger count = new AtomicInteger();

        publisher.subscribe(EventType.STOCK_CHANGED,
                event -> count.incrementAndGet());

        service.setStock("Phone", 10);
        service.setStock("Phone", 5);

        assertEquals(1, count.get());
    }

    @Test
    void testUnsubscribe() {
        EventPublisher publisher = new EventPublisher();
        AtomicInteger count = new AtomicInteger();

        Observer<Event> listener = event -> count.incrementAndGet();

        publisher.subscribe(EventType.PRICE_CHANGED, listener);
        publisher.unsubscribe(EventType.PRICE_CHANGED, listener);

        publisher.notifyObservers(
                new Event(EventType.PRICE_CHANGED, "Phone", 1000, 800)
        );
        assertEquals(0, count.get());
    }

    @Test
    void testEventFiltering() {
        EventPublisher publisher = new EventPublisher();
        AtomicInteger count = new AtomicInteger();

        publisher.subscribe(EventType.PRICE_CHANGED,
                event -> count.incrementAndGet());

        publisher.notifyObservers(
                new Event(EventType.STOCK_CHANGED, "Phone", 10, 5)
        );
        assertEquals(0, count.get());
    }

    @Test
    void testNegativePrice() {
        PriceService service = new PriceService(new EventPublisher());

        assertThrows(IllegalArgumentException.class,
                () -> service.setPrice("Phone", -10));
    }
}

