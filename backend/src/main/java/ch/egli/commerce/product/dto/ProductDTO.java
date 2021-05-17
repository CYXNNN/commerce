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
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Double price;

  @NotNull
  private Integer stock;

  @NotNull
  private ProductCategory category;

  private byte[] picture;

  private String fileType;

  private String fileName;

  private boolean deleted;

  public ProductDTO fromEntity(@Valid Product entity) {
    super.fromEntity(entity);
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.price = entity.getPrice();
    this.stock = entity.getStock();
    this.category = entity.getCategory();
    this.deleted = entity.isDeleted();
    this.picture = entity.getPicture();
    this.fileName = entity.getFileName();
    this.fileType = entity.getFileType();

    return this;
  }
}
