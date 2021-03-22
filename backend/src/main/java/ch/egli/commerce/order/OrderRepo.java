package ch.egli.commerce.order;

import ch.egli.commerce.config.Database;
import ch.egli.commerce.persistence.OrderProduct;
import ch.egli.commerce.persistence.PlacedOrder;
import ch.egli.commerce.persistence.QOrderProduct;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@RequestScoped
public class OrderRepo {


  private Database database;

  public OrderRepo() {
    // nope
  }

  @Inject
  public OrderRepo(Database database) {
    this.database = database;
  }

  public List<OrderProduct> getAll() {
    return database.query().from(QOrderProduct.orderProduct).fetch();
  }

  public void post(PlacedOrder order) {
    database.persist(order);
  }

  public OrderProduct put(OrderProduct order) {
    return database.merge(order);
  }

  public void delete(OrderProduct order) {
    database.remove(order);
  }

  public OrderProduct find(String id) {
    //FIXME error handling
    return database.find(OrderProduct.class, id).orElse(null);
  }
}
