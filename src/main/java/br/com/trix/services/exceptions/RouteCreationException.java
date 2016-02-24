package br.com.trix.services.exceptions;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 24/02/16.
 */
public class RouteCreationException extends DefaultValidationException {

  public RouteCreationException() {
    super("routes.creation_not_found.error" , "routes.creation_not_found.description");
  }
}
