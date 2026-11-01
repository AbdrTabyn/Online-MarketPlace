package visitor;
import marketplace.*;

public class ProductCountVisitor implements Visitor<Integer> {
    @Override
    public Integer visit(Catalog catalog) {
        int count = 0;
        for (Category category : catalog.getCategories()) {
            count += category.accept(this);
        }
        return count;
    }

    @Override
    public Integer visit(Category category) {
        int count = 0;
        for (Subcategory subcategory : category.getSubcategories()) {
            count += subcategory.accept(this);
        }
        return count;
    }

    @Override
    public Integer visit(Subcategory subcategory) {
        int count = 0;
        for (CatalogElement product : subcategory.getProducts()) {
            count += product.accept(this);
        }
        return count;
    }

    @Override
    public Integer visit(PhysicalProduct product) {
        return 1;
    }

    @Override
    public Integer visit(DigitalProduct product) {
        return 1;
    }

    @Override
    public Integer visit(ServiceProduct product) {
        return 1;
    }

    @Override
    public Integer visit(SubscriptionProduct product) {
        return 1;
    }

    @Override
    public Integer visit(GiftCardProduct product) {
        return 1;
    }
}
