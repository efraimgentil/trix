package br.com.trix.services.exceptions;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class NoWayPointOrderException extends DefaultValidationException {

  public NoWayPointOrderException() {
    super("routes.not_waypoint_order.error" , "routes.not_waypoint_order.description");
  }
}
