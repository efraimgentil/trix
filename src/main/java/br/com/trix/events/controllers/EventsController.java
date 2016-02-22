package br.com.trix.events.controllers;

import br.com.trix.events.services.EventService;
import br.com.trix.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@RestController
@RequestMapping("/events")
public class EventsController {

  @Autowired
  EventService eventService;

  @RequestMapping("/")
  public ResponseEntity<?> checkEvents(@RequestParam("vehicleId") String vehicleId
          , @RequestParam("lat") Double lat
          , @RequestParam("lng") Double lng ){
    eventService.checkEventOccurrence(vehicleId , new Position( lat , lng ) );
    return new ResponseEntity<Object>(HttpStatus.OK);
  }

}
