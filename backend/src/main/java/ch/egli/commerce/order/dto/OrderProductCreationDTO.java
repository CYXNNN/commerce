package ch.egli.commerce.order.dto;

import ch.egli.commerce.address.dto.AddressCreationDTO;
import ch.egli.commerce.enumeration.PaymentOption;
import ch.egli.commerce.persistence.OrderProduct;
import ch.egli.commerce.persistence.PlacedOrder;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.OrderProductDTO;
import java.util.List;
import java.util.stream.Collectors;
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
public class OrderProductCreationDTO {

  @NotNull
  @Size(min = 1)
  private List<OrderProductDTO> products;

  @NotNull
  private AddressCreationDTO senderAddress;

  @NotNull
  private AddressCreationDTO receiverAddress;

  private Long orderTotal;

  @NotNull
  private PaymentOption payment;

  public PlacedOrder toEntity() {
    PlacedOrder order = new PlacedOrder();

    order.setProducts(
      this.products.stream().map(OrderProductCreationDTO::orderProductToEntity)
        .collect(Collectors.toList()));

    order.setSenderAddress(this.senderAddress.toEntity());
    order.setReceiverAddress(this.receiverAddress.toEntity());

    return order;
  }

  public static OrderProduct orderProductToEntity(OrderProductDTO dto) {
    OrderProduct op = new OrderProduct();
    // FIXME fetch existing product from db to avoid conflict with hibernate session
    Product p = new Product();
    p.setId(dto.getId());
    op.setProduct(p);
    op.setQuantity(dto.getQuantity());

    return op;
  }

}
