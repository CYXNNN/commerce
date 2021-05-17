package ch.egli.commerce.user;

import ch.egli.commerce.address.dto.AddressCreationDTO;
import ch.egli.commerce.persistence.Address;
import ch.egli.commerce.persistence.User;
import ch.egli.commerce.security.Token;
import ch.egli.commerce.user.dto.LoginDTO;
import ch.egli.commerce.user.dto.RegisterDTO;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

  User find(@NotNull final String username);

  Token auth(@NotNull @Valid LoginDTO loginDto);

  void removeToken(String username);

  boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed);

  void post(User user);

  void register(RegisterDTO registerDto);

  Address getAddress(String username);

  void putAddress(String username, AddressCreationDTO addressDto);

}
