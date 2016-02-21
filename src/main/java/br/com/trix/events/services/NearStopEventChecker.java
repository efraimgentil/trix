package br.com.trix.events.services;

import br.com.trix.models.Position;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Service;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
public class NearStopEventChecker implements EventChecker {

  @Autowired
  private StopRepository stopRepository;

  @Override
  public void check(Vehicle vehicle, Position position) {
    //stopRepository.findByRouteIdAndPositionNear( vehicle.getCurrentRoute() , position );
  }
}
