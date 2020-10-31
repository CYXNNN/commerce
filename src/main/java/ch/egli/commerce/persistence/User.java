package ch.egli.commerce.persistence;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.security.auth.Subject;

public class User extends Persistence implements Principal {

  public void getCustomerData() {
    getZeitfensters().stream().map(SLAZeitfenster::merge).collect(Collectors.toList());
  }

  public List<User> users;

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean implies(Subject subject) {
    return false;
  }

  public static SLAZeitfenster merge(SLAZeitfensterDTO  zfdto) {
    var zf = new SLAZeitfenster();
    zf.setAusfuehrungsBedingung(ab);
    zfdto.apply(zf);

    return zf;
  }

}
