package ch.egli.commerce.exceptions;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Exception to be thrown when the password does not match.
 */
public class WrongPasswordException extends Exception {

  private final String username;

  /**
   * @param username the given username.
   * @throws NullPointerException
   */
  public WrongPasswordException(final String username) {
    super();
    this.username = notNull(username, "username must not be null");
  }

  @Override
  public String getMessage() {
    return String.format("Wrong password for user %s", username);
  }

  public String getUsername() {
    return username;
  }
}
