package ch.egli.commerce.authentication;

import ch.egli.commerce.exceptions.UserNotFoundException;
import ch.egli.commerce.exceptions.WrongPasswordException;
import ch.egli.commerce.persistence.User;
import ch.egli.commerce.user.UserRepo;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.validation.constraints.NotNull;

@ApplicationScoped
public class AuthenticationService implements Serializable {

  private static final Logger LOG = Logger.getLogger(AuthenticationService.class.getName());

  @Inject
  private UserRepo repository;

  public User authenticate(@NotNull final String username, @NotNull final String password)
    throws WrongPasswordException, UserNotFoundException {
    LOG.log(Level.FINE, "Authenticate user {0}", username);

    try {
      User user = repository.findByUsername(username);
      if (!isGivenPasswordEqual(user, password)) {
        throw new WrongPasswordException(username);
      }

      return user;
    } catch (NoResultException | NonUniqueResultException e) {
      throw new UserNotFoundException(username, e);
    }
  }

  private boolean isGivenPasswordEqual(final User user, final String password) {
    return user.getPasswordHash().equals(password);
  }
}
