package visitor;

import marketplace.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFilterVisitor implements Visitor<List<String>> {
    private final double maxPrice;
    public ProductFilterVisitor(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public List<String> visit(Catalog catalog) {
        List<String> result = new ArrayList<>();

        for (Category category : catalog.getCategories()) {
            result.addAll(category.accept(this));
        }
        return result;
    }

    @Override
    public List<String> visit(Category category) {
        List<String> result = new ArrayList<>();

        for (Subcategory sub : category.getSubcategories()) {
            result.addAll(sub.accept(this));
        }
        return result;
    }

    @Override
    public List<String> visit(Subcategory subcategory) {
        List<String> result = new ArrayList<>();

        for (CatalogElement product : subcategory.getProducts()) {
            result.addAll(product.accept(this));
        }
        return result;
    }

    @Override
    public List<String> visit(PhysicalProduct product) {
        return filter(product.getName(), product.getPrice());
    }

    @Override
    public List<String> visit(DigitalProduct product) {
        return filter(product.getName(), product.getPrice());
    }

    @Override
    public List<String> visit(ServiceProduct product) {
        return filter(product.getName(), product.getPrice());
    }

    @Override
    public List<String> visit(SubscriptionProduct product) {
        return filter(product.getName(), product.getPrice());
    }

    private List<String> filter(String name, double price) {
        if (price <= maxPrice) {
            return List.of(name);
        }
        return List.of();
    }

    @Override
    public List<String> visit(GiftCardProduct product) {
        List<String> result = new ArrayList<>();

        if (product.getPrice() <= maxPrice) {
            result.add(product.getName());
        }

        return result;
    }
}
