package listener;

import observer.Event;
import observer.Observer;

public class WishlistNotifier implements Observer<Event> {
    @Override
    public void onEvent(Event event) {
        System.out.println(
                "Wishlist: " + event.getProductName()
                        + " price changed from "
                        + event.getOldValue()
                        + " to " + event.getNewValue()
        );
    }
}
