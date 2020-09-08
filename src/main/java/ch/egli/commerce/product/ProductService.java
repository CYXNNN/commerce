package ch.egli.commerce.product;

import ch.egli.commerce.persistence.Product;
import java.util.Collection;

public interface ProductService {
  Collection<Product> getAll();
}
