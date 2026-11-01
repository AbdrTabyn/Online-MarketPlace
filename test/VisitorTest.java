import marketplace.*;
import visitor.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {

    private Catalog createCatalog() {
        Subcategory subcategory = new Subcategory(
                "Products",
                List.of(
                        new PhysicalProduct("Phone", 1000, 0.5),
                        new DigitalProduct("Game", 50),
                        new ServiceProduct("Installation", 30),
                        new SubscriptionProduct("Premium", 10, 12),
                        new GiftCardProduct("Gift Card", 20)
                )
        );
        Category category = new Category(
                "Electronics",
                List.of(subcategory)
        );
        return new Catalog(
                "Online Marketplace",
                List.of(category)
        );
    }

    @Test
    void testTax() {
        assertEquals(106.4, createCatalog().accept(new TaxVisitor()), 0.001);
    }

    @Test
    void testShipping() {
        assertEquals(2.5, createCatalog().accept(new ShippingVisitor()), 0.001);
    }

    @Test
    void testFilter() {
        List<String> result = createCatalog().accept(new ProductFilterVisitor(100));

        assertEquals(4, result.size());
        assertFalse(result.contains("Phone"));
    }

    @Test
    void testXml() {
        String xml = createCatalog().accept(new XmlExportVisitor());

        assertTrue(xml.contains("<catalog"));
        assertTrue(xml.contains("giftCard"));
    }

    @Test
    void testProductCount() {
        assertEquals(5,
                createCatalog().accept(new ProductCountVisitor()));
    }
}
