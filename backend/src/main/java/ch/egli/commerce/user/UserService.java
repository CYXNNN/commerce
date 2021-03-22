package ch.egli.commerce.user;

import ch.egli.commerce.exceptions.UserNotFoundException;
import ch.egli.commerce.persistence.User;
import javax.validation.constraints.NotNull;

public interface UserService {

  User find(@NotNull final String username) throws UserNotFoundException;

  void post(User user);

}
