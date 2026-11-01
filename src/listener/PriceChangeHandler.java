package listener;

import marketplace.*;
import observer.*;
import visitor.TaxVisitor;

public class PriceChangeHandler implements Observer<Event> {
    private final Catalog catalog;

    public PriceChangeHandler(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() != EventType.PRICE_CHANGED) {
            return;
        }

        for (Category category : catalog.getCategories()) {
            for (Subcategory sub : category.getSubcategories()) {
                for (CatalogElement product : sub.getProducts()) {

                    if (getProductName(product)
                            .equals(event.getProductName())) {
                        double tax = product.accept(new TaxVisitor());

                        System.out.println(
                                "Visitor triggered for "
                                        + event.getProductName()
                                        + ": tax = " + tax
                        );
                        return;
                    }
                }
            }
        }
    }

    private String getProductName(CatalogElement product) {
        if (product instanceof PhysicalProduct p) {
            return p.getName();
        }
        if (product instanceof DigitalProduct p) {
            return p.getName();
        }
        if (product instanceof ServiceProduct p) {
            return p.getName();
        }
        if (product instanceof SubscriptionProduct p) {
            return p.getName();
        }
        return "";
    }
}
