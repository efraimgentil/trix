package br.com.trix.events.services.exceptions;

import br.com.trix.services.exceptions.DefaultValidationException;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class EventWithoutPositionException extends DefaultValidationException {

  public EventWithoutPositionException() {
    super("event.without_position.error" , "event.without_position.description");
  }
}
