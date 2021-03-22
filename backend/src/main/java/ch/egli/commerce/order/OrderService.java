package ch.egli.commerce.order;

import ch.egli.commerce.order.dto.OrderProductCreationDTO;
import ch.egli.commerce.persistence.PlacedOrder;
import java.util.List;

public interface OrderService {

  void post(OrderProductCreationDTO dto);

  List<PlacedOrder> get(String id);

  List<PlacedOrder> get();

}
