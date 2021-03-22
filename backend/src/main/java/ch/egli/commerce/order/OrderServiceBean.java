package ch.egli.commerce.order;

import ch.egli.commerce.order.dto.OrderProductCreationDTO;
import ch.egli.commerce.persistence.PlacedOrder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class OrderServiceBean implements OrderService {

  private OrderRepo orderRepo;

  OrderServiceBean() {
    // nope
  }

  @Inject
  OrderServiceBean(OrderRepo orderRepo) {
    this.orderRepo = orderRepo;
  }

  @Override
  public void post(OrderProductCreationDTO dto) {
    PlacedOrder order = dto.toEntity();
    orderRepo.post(order);
  }

  @Override
  public List<PlacedOrder> get(String id) {
    return null;
  }

  @Override
  public List<PlacedOrder> get() {
    return null;
  }
}
