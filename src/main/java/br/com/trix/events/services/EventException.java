package br.com.trix.events.services;

import br.com.trix.services.exceptions.DefaultValidationException;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public class EventException extends DefaultValidationException {

  public EventException(){
    super("event.without_near_stop.error" , "event.without_near_stop.description");
  }
}
