package ch.egli.commerce.product.dto;

import ch.egli.commerce.enumeration.ProductCategory;
import javax.validation.constraints.NotNull;

public class ProductDTO {

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
}
