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

  public void fromEntity(AbstractDTO target, Persistence entity) {
    target.id = entity.getId();
    target.creationDate = entity.getCreationDate();
  }
}
