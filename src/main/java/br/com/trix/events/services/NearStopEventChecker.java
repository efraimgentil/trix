package br.com.trix.events.services;

import br.com.trix.events.models.Occurrence;
import br.com.trix.events.models.OccurrenceType;
import br.com.trix.models.Position;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.OccurrenceRepository;
import br.com.trix.repositories.StopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
public class NearStopEventChecker implements EventChecker {

  private static final Logger LOGGER = LoggerFactory.getLogger(NearStopEventChecker.class);

  @Autowired StopRepository stopRepository;
  @Autowired OccurrenceRepository occurrenceRepository;
  @Autowired MessageSource messageSource;

  @Override
  public Occurrence check(Vehicle vehicle, Position position) {
    PageRequest pageRequest = new PageRequest(0, 1);
    Page<Stop> page = stopRepository.findByRouteIdAndPositionNear(vehicle.getCurrentRoute(), position.toPoint(), pageRequest);
    List<Stop> content = page.getContent();
    if(content.isEmpty()) {
      LOGGER.error( "Was not possible to find the nearest Stop for the (VehicleId:" + vehicle.getId()
              + ", at position:" + position + ", in the route:" + vehicle.getCurrentRoute() + ")"  );
      throw new EventException();
    }else {
      return  occurrenceRepository.save( messageOccurrence(vehicle, position, content.get(0)) );
    }
  }

  protected Occurrence messageOccurrence(Vehicle vehicle, Position position, Stop stop){
    Occurrence oc = Occurrence.newOccurrence( vehicle , position , OccurrenceType.NEAREST_STOP );
    String message = messageSource.getMessage( oc.getType().toString() , new Object[]{ stop.getId() } , Locale.US );
    oc.setMessage(message);
    return oc;
  }

}
