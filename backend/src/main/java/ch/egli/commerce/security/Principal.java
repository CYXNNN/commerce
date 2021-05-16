package ch.egli.commerce.security;

import ch.egli.commerce.persistence.User;
import java.util.HashMap;
import java.util.Map;

public final class Principal {

  private static Principal INSTANCE;
  private Map<String, User> users = new HashMap<>();

  private Principal() {
  }

  public static Principal getInstance() {

    if (INSTANCE == null) {
      INSTANCE = new Principal();
    }

    return INSTANCE;
  }

  public void put(User user) {
    users.put(user.getUsername(), user);
  }

  public User caller(String username) {
    return users.get(username);
  }
}
