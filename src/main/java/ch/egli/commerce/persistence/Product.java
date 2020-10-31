package ch.egli.commerce.persistence;

import ch.egli.commerce.enumeration.ProductCategory;
import javax.persistence.Column;
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
  @Column (nullable = false)
  private String name;

  @NotNull
  @Column (nullable = false)
  private String description;

  @NotNull
  @Column (nullable = false)
  private Long price;

  @NotNull
  @Column (nullable = false)
  private Integer stock;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private ProductCategory category;
}
