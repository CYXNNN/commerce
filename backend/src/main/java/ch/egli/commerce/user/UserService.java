package ch.egli.commerce.user;

import ch.egli.commerce.exceptions.UserNotFoundException;
import ch.egli.commerce.persistence.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.validation.constraints.NotNull;

public class UserService {

  private static final Logger LOG = Logger.getLogger(UserService.class.getName());

  @Inject
  private UserRepo repository;

  public User find(@NotNull final String username) throws UserNotFoundException {
    LOG.log(Level.FINE, "Find user {0}", username);

    try {
      return repository.findByUsername(username);
    } catch (NoResultException | NonUniqueResultException e) {
      throw new UserNotFoundException(username, e);
    }
  }
}
