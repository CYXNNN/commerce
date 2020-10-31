package ch.egli.commerce.persistence;

import java.security.Principal;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Persistence {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column
  @NotNull
  private Date creationDate;

}
