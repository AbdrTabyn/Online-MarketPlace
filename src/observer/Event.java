package observer;

public final class Event {
    private final EventType type;
    private final String productName;
    private final double oldValue;
    private final double newValue;

    public Event(EventType type, String productName,
                 double oldValue, double newValue) {
        this.type = type;
        this.productName = productName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public EventType getType() {
        return type;
    }

    public String getProductName() {
        return productName;
    }

    public double getOldValue() {
        return oldValue;
    }

    public double getNewValue() {
        return newValue;
    }
}
