package listener;

import observer.Event;
import observer.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CacheListener implements Observer<Event> {
    private final Set<String> cachedProducts =
            ConcurrentHashMap.newKeySet();

    public void addToCache(String productName) {
        cachedProducts.add(productName);
    }

    @Override
    public void onEvent(Event event) {
        if (cachedProducts.remove(event.getProductName())) {
            System.out.println(
                    "Cache: removed outdated data for "
                            + event.getProductName()
            );
        }
    }
    public boolean isCached(String productName) {
        return cachedProducts.contains(productName);
    }
}
