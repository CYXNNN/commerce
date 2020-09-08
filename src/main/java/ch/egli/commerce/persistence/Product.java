package ch.egli.commerce.persistence;

import ch.egli.commerce.enumeration.ProductCategory;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Product extends Persistence {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Long price;

  @NotNull
  private Integer stock;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private ProductCategory category;

}
