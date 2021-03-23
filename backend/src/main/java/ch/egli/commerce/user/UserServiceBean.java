package ch.egli.commerce.user;

import ch.egli.commerce.exceptions.EntityNotFoundException;
import ch.egli.commerce.persistence.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@RequestScoped
@Transactional
public class UserServiceBean implements UserService {

  private static final Logger LOG = Logger.getLogger(UserServiceBean.class.getName());

  @Inject
  private UserRepo userRepo;

  @Override
  public User find(@NotNull final String username) throws EntityNotFoundException {
    LOG.log(Level.FINE, "Find user {0}", username);

    try {
      return userRepo.findByUsername(username);
    } catch (NoResultException | NonUniqueResultException e) {
      throw new EntityNotFoundException(username + " not found");
    }
  }

  @Override
  public void post(User user) {
    userRepo.post(user);
  }
}
