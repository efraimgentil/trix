package br.com.trix.events.controllers;

import br.com.trix.events.models.vo.EventRequest;
import br.com.trix.events.services.EventService;
import br.com.trix.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@RestController
@RequestMapping("/events")
public class EventsController {

  @Autowired
  EventService eventService;

  @RequestMapping(value = "/" , method = RequestMethod.POST)
  public ResponseEntity<?> checkEvents(@RequestBody EventRequest eventRequest){
    eventService.checkEventOccurrence( eventRequest );
    return new ResponseEntity<Object>(HttpStatus.OK);
  }

}
