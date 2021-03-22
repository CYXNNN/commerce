package ch.egli.commerce.order.dto;

import ch.egli.commerce.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDisplayDTO extends AbstractDTO {

  private String name;

  private String description;

  private Double price;

  private int quantity;

}
