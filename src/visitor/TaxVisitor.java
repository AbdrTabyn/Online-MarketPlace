package visitor;
import marketplace.*;

public class TaxVisitor implements Visitor<Double> {
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
        return product.getPrice() * 0.10;
    }

    @Override
    public Double visit(DigitalProduct product) {
        return product.getPrice() * 0.05;
    }

    @Override
    public Double visit(ServiceProduct product) {
        return product.getPrice() * 0.08;
    }

    @Override
    public Double visit(SubscriptionProduct product) {
        return product.getPrice() * 0.05;
    }
}
