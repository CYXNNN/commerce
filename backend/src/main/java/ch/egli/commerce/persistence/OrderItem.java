package ch.egli.commerce.persistence;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "order_item")
public class OrderItem extends Persistence {

  @NotNull
  @OneToOne(orphanRemoval = true, fetch = EAGER, cascade = ALL)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  // if the product price gets updated later, we store the current price
  // so we display the right information
  @NotNull
  @Column(nullable = false, name = "price_at_order")
  private Double priceAtOrder;

  @NotNull
  @Column(nullable = false, name = "quantity")
  private Integer quantity;

  public Double totalItemPrice() {
    return priceAtOrder * quantity;
  }

}
