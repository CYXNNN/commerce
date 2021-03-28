package ch.egli.commerce.product;

import ch.egli.commerce.config.Database;
import ch.egli.commerce.enumeration.ProductSortOptions;
import ch.egli.commerce.exceptions.EntityNotFoundException;
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

  public List<Product> getAll(ProductSortOptions options) {
    var query = database.queryFactory().from(QProduct.product);

    query.where(QProduct.product.deleted.eq(false));

    switch (options) {
      case PRICE_ASC:
        query.orderBy(QProduct.product.price.asc());
        break;
      case PRICE_DESC:
        query.orderBy(QProduct.product.price.desc());
        break;
      case CREATION_DATE_ASC:
        query.orderBy(QProduct.product.creationDate.asc());
        break;
      case CREATION_DATE_DESC:
        query.orderBy(QProduct.product.creationDate.desc());
        break;
    }

    return (List<Product>) query.fetch();
  }

  public List<Product> getNewest(int limit) {
    return (List<Product>) database.queryFactory().from(QProduct.product)
      .limit(limit)
      .where(QProduct.product.deleted.eq(false))
      .orderBy(QProduct.product.creationDate.desc())
      .fetch();
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
    return database
      .find(Product.class, id)
      .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
  }
}
