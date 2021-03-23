package ch.egli.commerce.order.dto;

import ch.egli.commerce.address.dto.AddressDTO;
import ch.egli.commerce.dto.AbstractDTO;
import ch.egli.commerce.enumeration.PaymentOption;
import ch.egli.commerce.enumeration.Shipment;
import ch.egli.commerce.persistence.Order;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDisplayDTO extends AbstractDTO {

  @NotNull
  @Size(min = 1)
  private List<OrderItemDisplayDTO> items = new ArrayList<>();

  @NotNull
  private AddressDTO billingAddress;

  @NotNull
  private AddressDTO senderAddress;

  @NotNull
  private PaymentOption payment;

  @NotNull
  @Min(0)
  private Double orderTotal;

  @NotNull
  private Shipment shipment;

  public OrderDisplayDTO fromEntity(Order order) {
    super.fromEntity(order);

    order.getItems().forEach(i ->
      this.items.add(new OrderItemDisplayDTO().fromEntity(i))
    );

    this.setBillingAddress(new AddressDTO().fromEntity(order.getBillingAddress()));
    this.setSenderAddress(new AddressDTO().fromEntity(order.getSenderAddress()));

    this.setOrderTotal(order.getOrderTotal());
    this.setShipment(order.getShipment());
    this.setPayment(order.getPaymentOption());

    return this;
  }

}
