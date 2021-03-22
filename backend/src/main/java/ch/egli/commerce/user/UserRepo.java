package ch.egli.commerce.user;

import ch.egli.commerce.config.Database;
import ch.egli.commerce.persistence.QUser;
import ch.egli.commerce.persistence.User;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@RequestScoped
public class UserRepo {

  private Database database;

  public UserRepo() {
    // nope
  }

  @Inject
  public UserRepo(Database database) {
    this.database = database;
  }

  public User findByUsername(String username) {
    return database.queryFactory().selectFrom(QUser.user)
      .where(QUser.user.username.eq(username))
      .fetchFirst();
  }

  public User findById(String id) {
    return database.queryFactory().selectFrom(QUser.user)
      .where(QUser.user.id.eq(id))
      .fetchFirst();
  }

  public Boolean existsByUsername(String username) {
    return database.queryFactory().selectFrom(QUser.user)
      .where(QUser.user.username.eq(username))
      .fetchFirst() != null;
  }

  public Boolean existsByEmail(String email) {
    return database.queryFactory().selectFrom(QUser.user)
      .where(QUser.user.email.eq(email))
      .fetchFirst() != null;
  }

  public void post(User user) {
    database.persist(user);
  }

}
