package ch.egli.commerce.product;

import ch.egli.commerce.config.Database;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.persistence.QProduct;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@RequestScoped
public class ProductRepo {

  private Database database;

  public ProductRepo() {
    // nope
  }

  @Inject
  public ProductRepo(Database database) {
    this.database = database;
  }

  public List<Product> getAll() {
    return database.query().from(QProduct.product).fetch();
  }

  public void post(Product product) {
    database.persist(product);
  }

  public Product put(Product product) {
    return database.merge(product);
  }

  public void delete(Product product) {
    database.remove(product);
  }

  public Product find(String id) {
    //FIXME error handling
    return database.find(Product.class, id).orElse(null);
  }
}
