package ch.egli.commerce.exceptions;

public abstract class BaseException extends RuntimeException {

  public BaseException(String message) {
    super(message);
  }
}
