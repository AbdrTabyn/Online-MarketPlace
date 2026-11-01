package visitor;
import marketplace.*;

public class ShippingVisitor implements Visitor<Double> {
    @Override
    public Double visit(Catalog catalog) {
        double total = 0;

        for (Category category : catalog.getCategories()) {
            total += category.accept(this);
        }
        return total;
    }

    @Override
    public Double visit(Category category) {
        double total = 0;

        for (Subcategory sub : category.getSubcategories()) {
            total += sub.accept(this);
        }
        return total;
    }

    @Override
    public Double visit(Subcategory subcategory) {
        double total = 0;

        for (CatalogElement product : subcategory.getProducts()) {
            total += product.accept(this);
        }
        return total;
    }

    @Override
    public Double visit(PhysicalProduct product) {
        return product.getWeight() * 5;
    }

    @Override
    public Double visit(DigitalProduct product) {
        return 0.0;
    }

    @Override
    public Double visit(ServiceProduct product) {
        return 0.0;
    }

    @Override
    public Double visit(SubscriptionProduct product) {
        return 0.0;
    }
}
