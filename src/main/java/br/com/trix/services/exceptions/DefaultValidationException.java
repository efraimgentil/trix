package br.com.trix.services.exceptions;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class DefaultValidationException extends RuntimeException {

  private String description;

  public DefaultValidationException(){
  }

  public DefaultValidationException(String message) {
    super(message);
  }

  public DefaultValidationException(String message, String description) {
    super(message);
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
