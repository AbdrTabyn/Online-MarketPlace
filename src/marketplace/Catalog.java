package marketplace;

import visitor.Visitor;
import java.util.List;

public class Catalog implements CatalogElement {
    private final String name;
    private final List<Category> categories;

    public Catalog(String name, List<Category> categories) {
        this.name = name;
        this.categories = List.copyOf(categories);
    }

    public String getName() {
        return name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
}
