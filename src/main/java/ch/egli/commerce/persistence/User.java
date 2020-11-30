package ch.egli.commerce.persistence;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.security.Principal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends Persistence implements Principal {

  @NotNull
  @Column(name = "username")
  private String username;

  @NotNull
  @Column(name = "password_hash")
  private String password_hash;

  @OneToOne(orphanRemoval = true, fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "user_address")
  private Address address;

  @Override
  public String getName() {
    return username;
  }

  @Override
  public boolean implies(Subject subject) {
    return false;
  }

}
