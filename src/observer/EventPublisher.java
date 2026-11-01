package observer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Map;

public class EventPublisher implements Subject<Event> {
    private final Map<EventType, CopyOnWriteArrayList<Observer<Event>>>
            observers = new ConcurrentHashMap<>();

    @Override
    public void subscribe(EventType type, Observer<Event> observer) {
        observers.computeIfAbsent(
                type,
                key -> new CopyOnWriteArrayList<>()
        ).addIfAbsent(observer);
    }

    @Override
    public void unsubscribe(EventType type, Observer<Event> observer) {
        List<Observer<Event>> listeners = observers.get(type);

        if (listeners != null) {
            listeners.remove(observer);
        }
    }

    @Override
    public void notifyObservers(Event event) {
        List<Observer<Event>> listeners = observers.get(event.getType());

        if (listeners != null) {
            for (Observer<Event> observer : listeners) {
                observer.onEvent(event);
            }
        }
    }
}
