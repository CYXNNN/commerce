package ch.egli.commerce.order;

import ch.egli.commerce.config.Database;
import ch.egli.commerce.persistence.Order;
import ch.egli.commerce.persistence.OrderItem;
import ch.egli.commerce.persistence.QOrder;
import ch.egli.commerce.persistence.User;
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

  public List<Order> getAll() {
    return (List<Order>) database.queryFactory()
      .from(QOrder.order)
      .orderBy(QOrder.order.creationDate.desc())
      .fetch();
  }

  public void post(Order order) {
    database.persist(order);
  }

  public OrderItem put(OrderItem order) {
    return database.merge(order);
  }

  public void delete(OrderItem order) {
    database.remove(order);
  }

  public OrderItem find(String id) {
    //FIXME error handling
    return database.find(OrderItem.class, id).orElse(null);
  }

  public List<Order> findByUser(User user) {
    return (List<Order>) database.queryFactory().from(QOrder.order)
      .where(QOrder.order.user.eq(user))
      .orderBy(QOrder.order.creationDate.desc())
      .fetch();
  }
}
