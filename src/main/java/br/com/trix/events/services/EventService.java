package br.com.trix.events.services;

import br.com.trix.events.models.vo.EventRequest;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.VehicleRepository;
import com.codahale.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jmx.export.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
@ManagedResource(objectName = "br.com.trix.events.services:name=EventService",
  description = "Handles all vehicle events")
public class EventService {

  private List<EventChecker> checkers;

  @Autowired VehicleRepository vehicleRepository;
  @Autowired NearStopEventChecker nearStopEventChecker;
  @Autowired OutOfRouteEventChecker outOfRouteEventChecker;

  @PostConstruct
  public void init(){
    checkers = Arrays.asList(nearStopEventChecker , outOfRouteEventChecker );
  }

  @Timed
  @ManagedOperation(  description = "Check all events in the event list")
  @ManagedOperationParameters( {
       @ManagedOperationParameter(name = "eventRequest", description = "The requisition of a event checking"),
  })
  public void checkEventOccurrence( EventRequest eventRequest ){
    Vehicle vehicle = vehicleRepository.findOne( eventRequest.getVehicleId()  );
    for (EventChecker checker : checkers){
      checker.check( vehicle , eventRequest.getPosition()  );
    }
  }

}
