package listener;

import observer.Event;
import observer.Observer;

public class PriceDropAlert implements Observer<Event> {
    @Override
    public void onEvent(Event event) {
        if (event.getNewValue() < event.getOldValue()) {
            System.out.println(
                    "Price Alert: " + event.getProductName()
                            + " is cheaper now!"
            );
        }
    }
}
