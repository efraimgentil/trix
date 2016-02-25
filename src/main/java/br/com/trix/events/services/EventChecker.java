package br.com.trix.events.services;

import br.com.trix.events.models.Occurrence;
import br.com.trix.models.Position;
import br.com.trix.models.Vehicle;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
public interface EventChecker {

  public Occurrence check(Vehicle vehicle, Position position );
}
