package br.com.trix.services.exceptions;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public class VehicleDoesnotExistException extends DefaultValidationException {

  public VehicleDoesnotExistException() {
    super("vehicle.not_found.error" , "vehicle.not_found.description");
  }
}
