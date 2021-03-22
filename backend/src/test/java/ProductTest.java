import ch.egli.commerce.enumeration.ProductCategory;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.ProductRepo;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

public class ProductTest {

  @Inject
  ProductRepo productRepo;

  @Test
  public void should_insert_product_into_database() {
    Product p = new Product();
    p.setName("test");
    p.setPrice(new Double(4.25));
    p.setCategory(ProductCategory.DRINK);
    p.setStock(200);
    p.setDescription("this is a very good product :)");

    //productRepo.post(p);
  }

}
