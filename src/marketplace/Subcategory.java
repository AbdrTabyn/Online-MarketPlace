package marketplace;

import visitor.Visitor;
import java.util.List;

public class Subcategory implements CatalogElement {
    private final String name;
    private final List<CatalogElement> products;

    public Subcategory(String name, List<CatalogElement> products) {
        this.name = name;
        this.products = List.copyOf(products);
    }

    public String getName() {
        return name;
    }

    public List<CatalogElement> getProducts() {
        return products;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
}
