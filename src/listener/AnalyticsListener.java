package listener;

import observer.Event;
import observer.Observer;
import java.util.concurrent.atomic.AtomicInteger;

public class AnalyticsListener implements Observer<Event> {
    private final AtomicInteger eventCount = new AtomicInteger();

    @Override
    public void onEvent(Event event) {
        eventCount.incrementAndGet();
    }
    public int getEventCount() {
        return eventCount.get();
    }
}
