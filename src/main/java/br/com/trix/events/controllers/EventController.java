package br.com.trix.events.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@RestController
@RequestMapping("/event")
public class EventController {


  public ResponseEntity<?> checkEvents(String vehicleId, Double lat, Double lng ){

    return new ResponseEntity<Object>(HttpStatus.OK);
  }

}
