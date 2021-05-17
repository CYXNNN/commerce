package ch.egli.commerce.security;

import ch.egli.commerce.persistence.User;
import java.util.HashMap;
import java.util.Map;

public final class Principal {

  private static Principal INSTANCE;
  private Map<String, String> users = new HashMap<>();

  private Principal() {
  }

  public static Principal getInstance() {

    if (INSTANCE == null) {
      INSTANCE = new Principal();
    }

    return INSTANCE;
  }

  public void put(String authToken, User user) {
    users.put(authToken, user.getUsername());
  }

  public String caller(String authToken) {
    return users.get(authToken);
  }
}
