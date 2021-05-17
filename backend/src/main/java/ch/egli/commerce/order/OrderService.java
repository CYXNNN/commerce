package ch.egli.commerce.order;

import ch.egli.commerce.order.dto.OrderCreationDTO;
import ch.egli.commerce.persistence.Order;
import java.util.List;

public interface OrderService {

  void post(OrderCreationDTO dto, String principal);

  List<Order> getByUser(String username);

  List<Order> get();

}
