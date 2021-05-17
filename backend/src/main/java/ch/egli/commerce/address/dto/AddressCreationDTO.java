package ch.egli.commerce.address.dto;

import ch.egli.commerce.persistence.Address;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreationDTO {

  @NotNull
  private String name = "";

  @NotNull
  private String prename = "";

  @NotNull
  private String street = "";

  @NotNull
  private String number = "";

  @NotNull
  private String zip = "";

  @NotNull
  private String city = "";

  public Address toEntity(Address ad) {
    ad.setName(this.getName());
    ad.setPrename(this.getPrename());
    ad.setStreet(this.getStreet());
    ad.setNumber(this.getNumber());
    ad.setZip(this.getZip());
    ad.setCity(this.getCity());

    return ad;
  }

}
