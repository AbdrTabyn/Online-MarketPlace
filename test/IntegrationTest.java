import marketplace.*;
import observer.*;
import visitor.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
public class IntegrationTest {

    @Test
    void testObserverTriggersVisitor() {
        PhysicalProduct phone = new PhysicalProduct("Phone", 1000, 0.5);
        EventPublisher publisher = new EventPublisher();
        AtomicReference<Double> tax = new AtomicReference<>();

        publisher.subscribe(EventType.PRICE_CHANGED,
                event -> tax.set(phone.accept(new TaxVisitor())));
        publisher.notifyObservers(
                new Event(EventType.PRICE_CHANGED, "Phone", 1000, 800)
        );

        assertEquals(100.0, tax.get(), 0.001);
    }

    @Test
    void testVisitorOnSubtree() {
        Subcategory subcategory = new Subcategory(
                "Phones",
                List.of(new PhysicalProduct("Phone", 1000, 0.5))
        );

        assertEquals(100.0,
                subcategory.accept(new TaxVisitor()), 0.001);
    }

    @Test
    void testObserverAndFilter() {
        DigitalProduct game = new DigitalProduct("Game", 50);
        EventPublisher publisher = new EventPublisher();
        AtomicInteger count = new AtomicInteger();

        publisher.subscribe(EventType.PRICE_CHANGED,
                event -> {
                    List<String> result =
                            game.accept(new ProductFilterVisitor(100));

                    count.set(result.size());
                });
        publisher.notifyObservers(
                new Event(EventType.PRICE_CHANGED, "Game", 60, 50)
        );

        assertEquals(1, count.get());
    }
}
