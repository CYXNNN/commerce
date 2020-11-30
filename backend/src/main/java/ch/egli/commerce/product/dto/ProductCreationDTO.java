package ch.egli.commerce.product.dto;

import ch.egli.commerce.enumeration.ProductCategory;
import ch.egli.commerce.persistence.Product;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class ProductCreationDTO {

  @NotNull
  private String name = "";

  @NotNull
  private String description = "";

  @NotNull
  private Long price = 0l;

  @NotNull
  private Integer stock = 0;

  @NotNull
  private ProductCategory category;

  public Product toEntity(Product target) {
    target.setCreationDate(new Date());
    target.setName(name);
    target.setDescription(description);
    target.setPrice(price);
    target.setStock(stock);
    target.setCategory(category);

    return target;
  }

}
