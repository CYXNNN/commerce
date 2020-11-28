package ch.egli.commerce.config;

import ch.egli.commerce.persistence.Persistence;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class Database {

  @Inject
  private EntityManager entityManager;

  public Database() {
    // nope
  }

  public JPAQuery query() {
    return new JPAQuery();
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

  public <Entity extends Persistence> Optional<Entity> find(Class<Entity> clazz, UUID id) {
    return Optional.of(entityManager.find(clazz, id));
  }
}
