package ch.egli.commerce.exceptions;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Exception to be thrown when an user is not found.
 */
public class UserNotFoundException extends Exception {

  private final String username;

  /**
   * @param username the given username.
   * @throws NullPointerException
   */
  public UserNotFoundException(final String username) {
    super();
    this.username = notNull(username, "username must not be null");
  }

  /**
   * @param username the given username.
   * @param cause    the cause
   * @throws NullPointerException
   */
  public UserNotFoundException(final String username, final Throwable cause) {
    super(cause);
    this.username = notNull(username, "username must not be null");
  }

  @Override
  public String getMessage() {
    return String.format("User %s was not found", username);
  }

  public String getUsername() {
    return username;
  }
}
