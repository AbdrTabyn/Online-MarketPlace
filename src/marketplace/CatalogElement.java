package marketplace;
import visitor.Visitor;

public interface CatalogElement {
    <R> R accept(Visitor<R> visitor);
}

