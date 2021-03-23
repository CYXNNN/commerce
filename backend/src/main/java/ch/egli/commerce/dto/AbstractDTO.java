package ch.egli.commerce.dto;

import ch.egli.commerce.persistence.Persistence;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDTO {

  @NotNull
  private String id;

  @NotNull
  private Date creationDate;

  @NotNull
  private Date modificationDate;

  public void fromEntity(Persistence entity) {
    this.id = entity.getId();
    this.creationDate = entity.getCreationDate();
    this.modificationDate = entity.getModificationDate();
  }
}
