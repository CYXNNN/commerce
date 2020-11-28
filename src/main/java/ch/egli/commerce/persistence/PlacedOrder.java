package ch.egli.commerce.persistence;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "placed_order")
public class PlacedOrder extends Persistence {

  @NotNull
  @Size(min = 1)
  @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = LAZY, cascade = ALL)
  private List<OrderProduct> products;

  @NotNull
  @OneToOne(orphanRemoval = true, fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "receiver_address_id", nullable = false)
  private Address receiverAddress;

  @NotNull
  @OneToOne(orphanRemoval = true, fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "sender_address_id", nullable = false)
  private Address senderAddress;
  
  @NotNull
  @Column(name = "order_total", nullable = false)
  private Long orderTotal;
}
