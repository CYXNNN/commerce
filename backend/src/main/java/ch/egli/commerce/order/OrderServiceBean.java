package ch.egli.commerce.order;

import ch.egli.commerce.order.dto.OrderCreationDTO;
import ch.egli.commerce.persistence.Order;
import ch.egli.commerce.persistence.OrderItem;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.persistence.User;
import ch.egli.commerce.product.ProductRepo;
import ch.egli.commerce.product.dto.OrderProductDTO;
import ch.egli.commerce.user.UserRepo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class OrderServiceBean implements OrderService {

  private static final Logger LOG = Logger.getLogger(OrderServiceBean.class.getName());

  private OrderRepo orderRepo;

  private ProductRepo productRepo;

  private UserRepo userRepo;

  OrderServiceBean() {
    // nope
  }

  @Inject
  OrderServiceBean(OrderRepo orderRepo, ProductRepo productRepo, UserRepo userRepo) {
    this.orderRepo = orderRepo;
    this.productRepo = productRepo;
    this.userRepo = userRepo;
  }

  @Override
  public void post(OrderCreationDTO dto) {

    Order order = new Order();

    List<OrderItem> products = createOrderProducts(dto, order);
    order.setItems(products);

    // calculate order total
    Double total = products.stream()
      .map(OrderItem::totalItemPrice)
      .reduce(new Double(0), Double::sum);

    // FIXME round price
    order.setOrderTotal(total);

    // FIXME Address may be present, dont create new if so
    order.setBillingAddress(dto.getBillingAddress().toEntity());
    order.setSenderAddress(dto.getSenderAddress().toEntity());

    // FIXME use principal when login is implemented
    order.setUser(userRepo.findByUsername("admin"));

    orderRepo.post(order);
  }

  private List<OrderItem> createOrderProducts(OrderCreationDTO dto, Order order) {
    List<OrderItem> products = dto.getProducts()
      .stream()
      .map(p -> createOrderProduct(p, order))
      .collect(Collectors.toList());
    return products;
  }

  private OrderItem createOrderProduct(OrderProductDTO p, Order order) {
    OrderItem op = new OrderItem();
    Product fetched = productRepo.find(p.getId());

    if (fetched == null) {
      LOG.log(Level.SEVERE, "Product with id: " + p.getId() + " is not present in the database");
      throw new NullPointerException("You tried to order a product which is not present");
    }

    op.setQuantity(p.getQuantity());
    op.setPriceAtOrder(fetched.getPrice());
    op.setOrder(order);
    op.setProduct(fetched);

    fetched.setStock(fetched.getStock() - op.getQuantity());

    return op;
  }

  @Override
  public List<Order> getByUser(String userId) {
    User user = userRepo.findById(userId);
    return orderRepo.findByUser(user);
  }

  @Override
  public List<Order> getByUser() {
    return null;
  }
}
