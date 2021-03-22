package ch.egli.commerce.order.dto;

import ch.egli.commerce.dto.AbstractDTO;
import ch.egli.commerce.persistence.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDisplayDTO extends AbstractDTO {

  private String name;

  private String description;

  private Double price;

  private int quantity;

  public OrderItemDisplayDTO fromEntity(OrderItem item) {

    super.fromEntity(this, item);

    this.setName(item.getProduct().getName());
    this.setDescription(item.getProduct().getDescription());
    this.setPrice(item.getPriceAtOrder() * item.getQuantity());
    this.setQuantity(item.getQuantity());

    return this;
  }

}
