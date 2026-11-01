import observer.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
public class ConcurrencyTest {

    @Test
    void testFourThreads() throws InterruptedException {
        EventPublisher publisher = new EventPublisher();
        StockService service = new StockService(publisher);
        AtomicInteger count = new AtomicInteger();

        publisher.subscribe(EventType.STOCK_CHANGED,
                event -> count.incrementAndGet());
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int thread = 0; thread < 4; thread++) {
            final int id = thread;

            executor.submit(() -> {
                String product = "Product-" + id;
                service.setStock(product, 0);

                for (int i = 1; i <= 1000; i++) {
                    service.setStock(product, i);
                }
            });
        }
        executor.shutdown();

        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS));
        assertEquals(4000, count.get());
    }


    @Test
    void testConcurrentSubscriptions() throws InterruptedException {
        EventPublisher publisher = new EventPublisher();
        AtomicInteger count = new AtomicInteger();

        Observer<Event> listener = event -> count.incrementAndGet();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                publisher.subscribe(EventType.PRICE_CHANGED, listener);
                publisher.unsubscribe(EventType.PRICE_CHANGED, listener);
            });
        }

        executor.shutdown();

        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS));

        publisher.subscribe(EventType.PRICE_CHANGED, listener);

        publisher.notifyObservers(
                new Event(EventType.PRICE_CHANGED, "Phone", 1000, 800)
        );

        assertEquals(1, count.get());
    }
}
