package ch.egli.commerce.order.dto;

import ch.egli.commerce.dto.AbstractDTO;
import ch.egli.commerce.persistence.OrderItem;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDisplayDTO extends AbstractDTO {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Double unitPrice;

  @NotNull
  private Double price;

  @NotNull
  private int quantity;

  @NotNull
  private String productId;

  public OrderItemDisplayDTO fromEntity(OrderItem item) {

    super.fromEntity(item);

    this.setName(item.getProduct().getName());
    this.setDescription(item.getProduct().getDescription());
    this.setUnitPrice(item.getPriceAtOrder());
    this.setPrice(item.totalItemPrice());
    this.setQuantity(item.getQuantity());
    this.setProductId(item.getProduct().getId());

    return this;
  }

}
