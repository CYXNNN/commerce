package ch.egli.commerce.product;

import ch.egli.commerce.enumeration.ProductSortOptions;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductCreationDTO;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class ProductServiceBean implements ProductService {

  private ProductRepo productRepo;

  public ProductServiceBean() {
    // nope
  }

  @Inject
  ProductServiceBean(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  @Override
  public Product find(String id) {
    return productRepo.find(id);
  }

  @Override
  public Collection<Product> getAll(ProductSortOptions options) {
    return productRepo.getAll(options);
  }

  @Override
  public Collection<Product> getNewest(int limit) {
    return productRepo.getNewest(limit);
  }

  @Override
  public Product update(ProductCreationDTO productDTO) {
    Product target = productRepo.find(productDTO.getId());
    return productRepo.put(productDTO.toEntity(target));
  }

  @Override
  public Product post(ProductCreationDTO creationDTO) {

    Product p = creationDTO.toEntity(new Product());
    productRepo.post(p);

    return p;
  }

  @Override
  public void delete(String id) {
    Product toDelete = productRepo.find(id);
    toDelete.setDeleted(true);
    productRepo.put(toDelete);
  }
}
