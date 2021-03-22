package ch.egli.commerce.order.dto;

import ch.egli.commerce.address.dto.AddressDTO;
import ch.egli.commerce.enumeration.PaymentOption;
import java.util.List;
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
public class OrderDisplayDTO {

  @NotNull
  @Size(min = 1)
  private List<OrderItemDisplayDTO> items;

  @NotNull
  private AddressDTO billingAddress;

  @NotNull
  private AddressDTO senderAddress;

  @NotNull
  private PaymentOption payment;

  private Double orderTotal;

}
