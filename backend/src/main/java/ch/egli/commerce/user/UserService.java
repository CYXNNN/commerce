package ch.egli.commerce.user;

import ch.egli.commerce.persistence.User;
import ch.egli.commerce.security.Token;
import ch.egli.commerce.user.dto.LoginDTO;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

  User find(@NotNull final String username);

  Token auth(@NotNull @Valid LoginDTO loginDto);

  boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed);

  void post(User user);

}
