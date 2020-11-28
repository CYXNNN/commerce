package ch.egli.commerce.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

  @Produces
  @PersistenceContext(unitName = "commerce_unit")
  private EntityManager em;

}
