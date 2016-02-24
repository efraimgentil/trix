package br.com.trix.services.exceptions;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public class VehicleDoesNotExistException extends DefaultValidationException {

  public VehicleDoesNotExistException() {
    super("vehicle.not_found.error" , "vehicle.not_found.description");
  }
}
