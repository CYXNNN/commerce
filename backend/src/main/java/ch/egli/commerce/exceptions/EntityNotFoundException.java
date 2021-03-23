package ch.egli.commerce.exceptions;

/**
 * Exception to be thrown when an user is not found.
 */
public class EntityNotFoundException extends BaseException {

  public EntityNotFoundException(String message) {
    super(message);
  }

}
