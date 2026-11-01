package listener;

import observer.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MarketplaceAggregator implements Observer<Event> {
    private final EventPublisher publisher;
    private final AtomicInteger totalEvents = new AtomicInteger();

    public MarketplaceAggregator(EventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.MARKETPLACE_SUMMARY) {
            return;
        }
        int count = totalEvents.incrementAndGet();

        publisher.notifyObservers(
                new Event(
                        EventType.MARKETPLACE_SUMMARY,
                        event.getProductName(),
                        count - 1,
                        count
                )
        );
    }
    public int getTotalEvents() {
        return totalEvents.get();
    }
}
