package ch.egli.commerce.authentication;


import static org.apache.commons.lang3.Validate.notNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Credentials for authentication.
 */
@XmlRootElement
public class Credentials {

  private String username;

  private String password;

  public Credentials() {
  }

  public Credentials(final String username, final String password) {
    this.username = notNull(username, "username must not be null");
    this.password = notNull(password, "password must not be null");
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Credentials{" +
      "username='" + username + '\'' +
      '}';
  }
}
