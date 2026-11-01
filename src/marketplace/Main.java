package marketplace;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PhysicalProduct phone = new PhysicalProduct("Phone", 1000, 0.5);
        DigitalProduct game = new DigitalProduct("Game", 50);
        ServiceProduct installation = new ServiceProduct("Installation", 30);
        SubscriptionProduct premium = new SubscriptionProduct("Premium", 10, 12);

        Subcategory products = new Subcategory(
                "Products",
                List.of(phone, game, installation, premium)
        );

        Category electronics = new Category(
                "Electronics",
                List.of(products)
        );

        Catalog catalog = new Catalog(
                "Online Marketplace",
                List.of(electronics)
        );

        System.out.println(catalog.getName());
        System.out.println(
                catalog.getCategories().get(0).getName()
        );
    }
}
