package ch.egli.commerce.persistence;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@MappedSuperclass
public abstract class Persistence {

  @NotNull
  @Column(name = "id", length = 64)
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @NotNull
  @Column(name = "creation_date")
  private Date creationDate = new Date();

  @NotNull
  @Column(name = "modification_date")
  private Date modificationDate = new Date();

}
