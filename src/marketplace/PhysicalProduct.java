package marketplace;
import visitor.Visitor;

public class PhysicalProduct implements CatalogElement {
    private final String name;
    private final double price;
    private final double weight;

    public PhysicalProduct(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
}
