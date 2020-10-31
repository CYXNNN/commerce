package ch.egli.commerce.product.dto;

import ch.egli.commerce.dto.AbstractDTO;
import ch.egli.commerce.enumeration.ProductCategory;
import ch.egli.commerce.persistence.Product;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class ProductDTO extends AbstractDTO {

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

  public ProductDTO fromEntity(@Valid Product entity) {
    super.fromEntity(this, entity);
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.price = entity.getPrice();
    this.stock = entity.getStock();
    this.category = entity.getCategory();

    return this;
  }

  public Product toEntity(Product target) {
    target.setName(name);
    target.setDescription(description);
    target.setPrice(price);
    target.setStock(stock);
    target.setCategory(category);

    return target;
  }
}
