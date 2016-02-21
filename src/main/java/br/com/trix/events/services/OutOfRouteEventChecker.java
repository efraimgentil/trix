package br.com.trix.events.services;

import br.com.trix.models.Position;
import br.com.trix.models.Vehicle;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
public class OutOfRouteEventChecker implements EventChecker {

  @Override
  public void check(Vehicle vehicle, Position position) {

  }

}
