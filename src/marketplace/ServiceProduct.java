package marketplace;
import visitor.Visitor;

public class ServiceProduct implements CatalogElement {
    private final String name;
    private final double price;

    public ServiceProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
}
