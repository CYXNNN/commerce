package ch.egli.commerce.order.dto;

import ch.egli.commerce.address.dto.AddressDTO;
import ch.egli.commerce.enumeration.PaymentOption;
import ch.egli.commerce.product.dto.OrderProductDTO;
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
public class OrderCreationDTO {

  @NotNull
  @Size(min = 1)
  private List<OrderProductDTO> products;

  @NotNull
  private AddressDTO billingAddress;

  @NotNull
  private AddressDTO senderAddress;

  @NotNull
  private PaymentOption payment;

}
