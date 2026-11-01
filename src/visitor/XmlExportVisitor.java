package visitor;
import marketplace.*;

public class XmlExportVisitor implements Visitor<String> {
    @Override
    public String visit(Catalog catalog) {
        StringBuilder xml = new StringBuilder();

        xml.append("<catalog name=\"")
                .append(escape(catalog.getName()))
                .append("\">\n");

        for (Category category : catalog.getCategories()) {
            xml.append(category.accept(this));
        }
        xml.append("</catalog>\n");
        return xml.toString();
    }

    @Override
    public String visit(Category category) {
        StringBuilder xml = new StringBuilder();

        xml.append("  <category name=\"")
                .append(escape(category.getName()))
                .append("\">\n");

        for (Subcategory sub : category.getSubcategories()) {
            xml.append(sub.accept(this));
        }
        xml.append("  </category>\n");
        return xml.toString();
    }

    @Override
    public String visit(Subcategory subcategory) {
        StringBuilder xml = new StringBuilder();

        xml.append("    <subcategory name=\"")
                .append(escape(subcategory.getName()))
                .append("\">\n");

        for (CatalogElement product : subcategory.getProducts()) {
            xml.append(product.accept(this));
        }
        xml.append("    </subcategory>\n");
        return xml.toString();
    }

    @Override
    public String visit(PhysicalProduct product) {
        return "      <physical name=\"" + escape(product.getName())
                + "\" price=\"" + product.getPrice()
                + "\" weight=\"" + product.getWeight()
                + "\" />\n";
    }

    @Override
    public String visit(DigitalProduct product) {
        return "      <digital name=\"" + escape(product.getName())
                + "\" price=\"" + product.getPrice()
                + "\" />\n";
    }

    @Override
    public String visit(ServiceProduct product) {
        return "      <service name=\"" + escape(product.getName())
                + "\" price=\"" + product.getPrice()
                + "\" />\n";
    }

    @Override
    public String visit(SubscriptionProduct product) {
        return "      <subscription name=\"" + escape(product.getName())
                + "\" price=\"" + product.getPrice()
                + "\" months=\"" + product.getMonths()
                + "\" />\n";
    }

    private String escape(String text) {
        return text.replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "&apos;");
    }

    @Override
    public String visit(GiftCardProduct product) {
        return "<giftCard name=\"" + product.getName()
                + "\" price=\"" + product.getPrice()
                + "\" />\n";
    }
}
