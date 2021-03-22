package ch.egli.commerce.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "address")
public class Address extends Persistence {

  @NotNull
  @Column(nullable = false, name = "name")
  private String name;

  @NotNull
  @Column(nullable = false, name = "prename")
  private String prename;

  @NotNull
  @Column(nullable = false, name = "street")
  private String street;

  @NotNull
  @Column(nullable = false, name = "number")
  private String number;

  @NotNull
  @Column(nullable = false, name = "zip")
  private String zip;

  @NotNull
  @Column(nullable = false, name = "city")
  private String city;

}
