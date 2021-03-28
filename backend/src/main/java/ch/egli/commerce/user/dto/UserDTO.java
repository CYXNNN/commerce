package ch.egli.commerce.user.dto;

import ch.egli.commerce.dto.AbstractDTO;
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
public class UserDTO extends AbstractDTO {

  private String username;
  private String email;
  private UserRole role;


  public void fromEntity(User user) {

    super.fromEntity(user);

    this.setUsername(user.getUsername());
    this.setEmail(user.getEmail());
    this.setRole(user.getRole());

  }
}
