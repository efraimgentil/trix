package br.com.trix.services.exceptions;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
public class RouteDoesNotExistException extends DefaultValidationException {

  public RouteDoesNotExistException() {
    super("route.not_found.error" , "route.not_found.description");
  }

}
