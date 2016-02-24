package br.com.trix.services.exceptions;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class NoPositionFoundException extends DefaultValidationException {

  public NoPositionFoundException() {
    super("event.without_position.error" , "event.without_position.description");
  }
}
