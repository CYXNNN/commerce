package ch.egli.commerce.persistence;

import ch.egli.commerce.enumeration.ProductCategory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends Persistence {

  @Column(name = "deleted", nullable = false)
  private boolean deleted;

  @NotNull
  @Column(nullable = false, name = "name")
  private String name;

  @NotNull
  @Column(nullable = false, name = "description", length = 2000)
  private String description;

  @NotNull
  @Column(nullable = false, name = "price")
  private Double price;

  @NotNull
  @Min(0)
  @Column(nullable = false, name = "stock")
  private Integer stock;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false, name = "category")
  private ProductCategory category;
}
