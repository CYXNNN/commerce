package ch.egli.commerce.product;

import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductCreationDTO;
import ch.egli.commerce.product.dto.ProductDTO;
import java.util.Collection;
import java.util.UUID;

public interface ProductService {

  Collection<Product> getAll();

  Product find(UUID id);

  Product update(ProductDTO productDTO);

  void post(ProductCreationDTO productCreationDTO);

  void delete(UUID id);
}
