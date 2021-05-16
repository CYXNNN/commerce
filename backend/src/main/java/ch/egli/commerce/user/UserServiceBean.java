package ch.egli.commerce.user;

import ch.egli.commerce.exceptions.EntityNotFoundException;
import ch.egli.commerce.persistence.User;
import ch.egli.commerce.security.Principal;
import ch.egli.commerce.security.Token;
import ch.egli.commerce.user.dto.LoginDTO;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestScoped
@Transactional
public class UserServiceBean implements UserService {

  private static final Logger LOG = Logger.getLogger(UserServiceBean.class.getName());

  @Inject
  private UserRepo userRepo;

  @Override
  public User find(@NotNull final String username) {
    try {
      return userRepo.findByUsername(username);
    } catch (NoResultException | NonUniqueResultException e) {
      throw new EntityNotFoundException(username + " not found");
    }
  }

  @Override
  public Token auth(@NotNull @Valid LoginDTO loginDto) {
    User user = userRepo
      .findByUsernameAndPassword(loginDto.getUsername(), loginDto.getHash());
    if (user != null) {
      var authToken = UUID.randomUUID().toString();
      user.setAuthToken(authToken);
      userRepo.put(user);
      Principal.getInstance().put(user);
      return new Token(loginDto.getUsername(), authToken, user.getRole().toString());
    }
    return null;
  }

  @Override
  public boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed) {

    if (authId == null || authToken == null) {
      return false;
    }

    User user = userRepo.findByUsernameAndAuthToken(authId, authToken);

    if (user != null) {
      return rolesAllowed.contains(user.getRole().toString());
    } else {
      return false;
    }
  }

  @Override
  public void post(User user) {
    userRepo.post(user);
  }
}
