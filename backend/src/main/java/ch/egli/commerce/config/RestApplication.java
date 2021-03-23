package ch.egli.commerce.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApplication extends Application {

  /*@Override
  public Set<Object> getSingletons() {
    Set<Object> set = new HashSet<>();
    set.add(new BaseExceptionMapper());
    return set;
  }*/
}
