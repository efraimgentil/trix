package br.com.trix.events.services;

import br.com.trix.events.models.Occurrence;
import br.com.trix.models.Position;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.OccurrenceRepository;
import br.com.trix.repositories.StopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
public class NearStopEventChecker implements EventChecker {


  private static final Logger LOGGER = LoggerFactory.getLogger(NearStopEventChecker.class);

  @Autowired StopRepository stopRepository;
  @Autowired OccurrenceRepository occurrenceRepository;

  @Override
  public void check(Vehicle vehicle, Position position) {
    PageRequest pageRequest = new PageRequest(0, 1);
    Page<Stop> page = stopRepository.findByRouteIdAndPositionNear(vehicle.getCurrentRoute(), position.toPoint(), new Distance(100), pageRequest);
    List<Stop> content = page.getContent();
    if(content.isEmpty()) {
      throw new EventException( "Was not possible to find the nearest Stop for the (VehicleId:" + vehicle.getId()
              + ", at position:" + position + ", in the route:" + vehicle.getCurrentRoute() + ")" );
    }else {
      occurrenceRepository.save( messageOccurrence(vehicle, position, content.get(0)) );
    }
  }

  protected Occurrence messageOccurrence(Vehicle vehicle, Position position, Stop stop){
    Occurrence occurrence = new Occurrence();
    occurrence.setLogDate( new Date() );
    occurrence.setCurrentVehiclePosition( position );
    occurrence.setVehicleId( vehicle.getId() );
    occurrence.setMessage("");
    return occurrence;
  }

}
