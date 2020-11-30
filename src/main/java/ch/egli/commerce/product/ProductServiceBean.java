package ch.egli.commerce.product;

import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductCreationDTO;
import ch.egli.commerce.product.dto.ProductDTO;
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
  public Collection<Product> getAll() {
    return productRepo.getAll();
  }

  @Override
  public Product update(ProductDTO productDTO) {
    Product target = productRepo.find(productDTO.getId());
    return productRepo.put(productDTO.toEntity(target));
  }

  @Override
  public void post(ProductCreationDTO creationDTO) {

    Product p = creationDTO.toEntity(new Product());

    System.out.println(p.getId());

    productRepo.post(p);
  }

  @Override
  public void delete(String id) {
    Product toDelete = productRepo.find(id);
    productRepo.delete(toDelete);
  }
}
