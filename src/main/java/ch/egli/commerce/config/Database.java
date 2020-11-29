package ch.egli.commerce.config;

import ch.egli.commerce.persistence.Persistence;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Christian Egli
 * <br>Helper class with generics for basic CRUD operations
 */
@Stateless
public class Database {

  @PersistenceContext(unitName = "commerce_unit")
  private EntityManager entityManager;

  public Database() {
    // nope
  }

  public JPAQuery query() {
    return new JPAQuery(entityManager);
  }

  public void flush() {
    entityManager.flush();
  }

  public <Entity extends Persistence> Entity merge(Entity entity) {
    return entityManager.merge(entity);
  }

  public <Entity extends Persistence> void persist(Entity entity) {
    entityManager.persist(entity);
  }

  public <Entity extends Persistence> void remove(Entity entity) {
    entityManager.remove(entity);
  }

  public <Entity> Optional<Entity> find(Class<Entity> clazz, String id) {
    Entity res = entityManager.find(clazz, id);

    if (res != null) {
      return Optional.ofNullable(res);
    }

    return Optional.ofNullable(null);
  }
}
