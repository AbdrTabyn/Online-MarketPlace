package visitor;
import marketplace.*;

public interface Visitor<R> {
    R visit(Catalog catalog);
    R visit(Category category);
    R visit(Subcategory subcategory);
    R visit(PhysicalProduct product);
    R visit(DigitalProduct product);
    R visit(ServiceProduct product);
    R visit(SubscriptionProduct product);
    R visit(GiftCardProduct product);
}
