package br.com.trix.events.services;

import br.com.trix.events.models.Occurrence;
import br.com.trix.events.models.OccurrenceType;
import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.OccurrenceRepository;
import br.com.trix.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.GeoCommand;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
public class OutOfRouteEventChecker implements EventChecker {

  @Autowired RouteRepository routeRepository;
  @Autowired OccurrenceRepository occurrenceRepository;
  @Autowired MessageSource messageSource;

  private final double acceptableRange = 0.5; //

  @Override
  public void check(Vehicle vehicle, Position position) {
    GeoResults<Route> routes = routeRepository.findRouteNearPosition(vehicle, position, acceptableRange);;
    if(routes.getContent().isEmpty()){
      occurrenceRepository.save( mountOccurrence(vehicle, position) );
    }
  }

  protected Occurrence mountOccurrence(Vehicle vehicle, Position position){
    Occurrence oc = Occurrence.newOccurrence(vehicle , position , OccurrenceType.OUT_OF_ROUTE );
    String message = messageSource.getMessage( oc.getType().toString() , new Object[]{} , Locale.US );
    oc.setMessage(message);
    return oc;
  }

}
