package ch.egli.commerce.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_product")
public class OrderProduct extends Persistence {

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
    orphanRemoval = true, optional = false)
  @JoinColumn(name = "order_product", nullable = false)
  @NotNull
  private Product product;

  @NotNull
  @Column(nullable = false, name = "quantity")
  private Integer quantity;

}
