package ch.egli.commerce.user.dto;

import ch.egli.commerce.enumeration.UserRole;
import ch.egli.commerce.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO extends LoginDTO {

  private String email;

  public User toEntity() {
    User user = new User();
    user.setRole(UserRole.ROLE_USER);
    user.setEmail(getEmail());
    user.setUsername(getUsername());
    user.setPasswordHash(getHash());

    return user;
  }

}
