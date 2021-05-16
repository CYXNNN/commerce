package ch.egli.commerce.persistence;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import ch.egli.commerce.enumeration.UserRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends Persistence {

  @NotNull
  @Column(name = "username", unique = true)
  private String username;

  @NotNull
  @Column(name = "password_hash")
  private String passwordHash;

  @NotNull
  @Column(name = "email", unique = true)
  private String email;

  // at the moment a user only can hold one role
  @Column(name = "role", nullable = false)
  private UserRole role;

  @Column(name = "auth_token")
  private String authToken;

  @OneToOne(orphanRemoval = true, fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "user_address")
  private Address address;

}
