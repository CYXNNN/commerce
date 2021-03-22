package ch.egli.commerce.persistence;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import ch.egli.commerce.enumeration.PaymentOption;
import ch.egli.commerce.enumeration.Shipment;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "`order`")
public class Order extends Persistence {

  @NotNull
  @Size(min = 1)
  @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = LAZY, cascade = ALL)
  private List<OrderItem> items;

  @NotNull
  @OneToOne(orphanRemoval = true, fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "billing_address_id", nullable = false)
  private Address billingAddress;

  @NotNull
  @OneToOne(orphanRemoval = true, fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "sender_address_id", nullable = false)
  private Address senderAddress;

  @NotNull
  @Column(name = "order_total", nullable = false)
  private Double orderTotal;

  @NotNull
  @OneToOne(orphanRemoval = true, fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull
  @Column(nullable = false, name = "payment_option")
  private PaymentOption paymentOption = PaymentOption.BILL;

  @NotNull
  @Column(nullable = false, name = "shipment")
  @Enumerated(EnumType.STRING)
  private Shipment shipment = Shipment.WAITING;
}
