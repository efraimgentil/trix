package br.com.trix.services;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public class StopsTooFarException extends RuntimeException {

  public StopsTooFarException(String message) {
    super(message);
  }
}
