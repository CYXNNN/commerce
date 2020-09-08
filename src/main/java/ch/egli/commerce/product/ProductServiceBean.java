package ch.egli.commerce.product;

import ch.egli.commerce.persistence.Product;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class ProductServiceBean implements ProductService {

  private ProductRepo productRepo;

  @Inject
  ProductServiceBean(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  public Collection<Product> getAll() {
    // TODO
    return null;
  }
}
