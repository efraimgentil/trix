package br.com.trix.events.services;

import br.com.trix.models.Position;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jmx.export.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
@ManagedResource(objectName = "br.com.trix.events.services:name=EventService",
description = "Handles all vehicle events")
public class EventService {

  private List<EventChecker> checkers;

  private AtomicLong executionCount = new AtomicLong(0);
  private Long lastExecutionTime;

  @Autowired VehicleRepository vehicleRepository;
  @Autowired NearStopEventChecker nearStopEventChecker;
  @Autowired OutOfRouteEventChecker outOfRouteEventChecker;

  @PostConstruct
  public void init(){
    checkers = Arrays.asList(nearStopEventChecker , outOfRouteEventChecker );
  }

  public void incrementExecutionCount(){
    executionCount.incrementAndGet();
  }
  public void setLastExecutionTime(Long lastExecutionTime){
    this.lastExecutionTime = lastExecutionTime;
  }
  @ManagedAttribute(description = "Execution Time in Millis", defaultValue = " - " )
  public Long getExecutionTime(){
    return lastExecutionTime;
  }
  @ManagedAttribute(description = "Execution Count", defaultValue = "0" )
  public Long getExecutionCount(){
    return executionCount.get();
  }


  @ManagedOperation(  description = "Check all events in the event list")
  @ManagedOperationParameters( {
       @ManagedOperationParameter(name = "vehicleId", description = "The vehicle to check"),
       @ManagedOperationParameter(name = "position", description = "The position where are the vehicle")
  })
  public void checkEventOccurrence(String vehicleId, Position position){
    Vehicle vehicle = vehicleRepository.findOne(vehicleId );
    for (EventChecker checker : checkers){
      checker.check( vehicle , position  );
    }
  }

}
