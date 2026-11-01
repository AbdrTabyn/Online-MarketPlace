package marketplace;
import visitor.Visitor;

public class SubscriptionProduct implements CatalogElement {
    private final String name;
    private final double price;
    private final int months;

    public SubscriptionProduct(String name, double price, int months) {
        this.name = name;
        this.price = price;
        this.months = months;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getMonths() {
        return months;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
}
