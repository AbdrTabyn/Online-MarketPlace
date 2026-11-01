package marketplace;

import visitor.Visitor;
import java.util.List;

public class Category implements CatalogElement {
    private final String name;
    private final List<Subcategory> subcategories;

    public Category(String name, List<Subcategory> subcategories) {
        this.name = name;
        this.subcategories = List.copyOf(subcategories);
    }

    public String getName() {
        return name;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
}
