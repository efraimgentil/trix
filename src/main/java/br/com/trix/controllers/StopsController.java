package br.com.trix.controllers;

import br.com.trix.models.Stop;
import br.com.trix.repositories.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@RestController
@RequestMapping("/stops")
public class StopsController {


  @Autowired
  protected StopRepository stopRepository;

  @RequestMapping(value = "/" , method = RequestMethod.GET)
  public ResponseEntity<List<Stop>> stops()
  {
    return new ResponseEntity<>( stopRepository.findAll() , HttpStatus.OK );
  }

  @RequestMapping(value = "/" , method = RequestMethod.POST)
  public ResponseEntity<Stop> insert(@RequestBody Stop stop)
  {
    stopRepository.save(stop);
    return new ResponseEntity<Stop>(stop , HttpStatus.OK );
  }


}
