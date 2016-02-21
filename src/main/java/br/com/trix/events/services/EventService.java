package br.com.trix.events.services;

import br.com.trix.models.Position;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
public class EventService {

  private List<EventChecker> checkers;

  @Autowired VehicleRepository vehicleRepository;
  @Autowired NearStopEventChecker nearStopEventChecker;
  @Autowired OutOfRouteEventChecker outOfRouteEventChecker;

  @PostConstruct
  public void init(){
    checkers = Arrays.asList(nearStopEventChecker , outOfRouteEventChecker );
  }

  public void checkEventOccurrence(String vehicleId , Position position){
    Vehicle vehicle = vehicleRepository.findOne(vehicleId );
    for (EventChecker checker : checkers){
      checker.check( vehicle , position  );
    }
  }

}
