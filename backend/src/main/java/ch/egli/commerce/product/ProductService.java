package ch.egli.commerce.product;

import ch.egli.commerce.enumeration.ProductSortOptions;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductCreationDTO;
import java.util.Collection;

public interface ProductService {

  Collection<Product> getAll(ProductSortOptions options);

  Collection<Product> getNewest(int limit);

  Product find(String id);

  Product update(ProductCreationDTO productDTO);

  Product post(ProductCreationDTO productCreationDTO);

  void delete(String id);
}
