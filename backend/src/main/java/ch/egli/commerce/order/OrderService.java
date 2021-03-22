package ch.egli.commerce.order;

import ch.egli.commerce.order.dto.OrderCreationDTO;
import ch.egli.commerce.persistence.Order;
import java.util.List;

public interface OrderService {

  void post(OrderCreationDTO dto);

  List<Order> getByUser(String userId);

  List<Order> getByUser();

}
