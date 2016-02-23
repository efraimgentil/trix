package br.com.trix.controllers;

import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import br.com.trix.models.vo.GenerateRoute;
import br.com.trix.repositories.RouteRepository;
import br.com.trix.services.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@RestController
@RequestMapping("/routes")
public class RoutesController {

  @Autowired
  protected RouteRepository routeRepository;
  @Autowired
  protected RoutesService routesService;

  @RequestMapping(value = "/" , method = RequestMethod.GET)
  public List<Route> routes(){
    return routeRepository.findAll();
  }

  @RequestMapping(value = "/{id}/" , method = RequestMethod.GET)
  public Route route(@PathVariable("id") String id){
    return routeRepository.findOne(id);
  }

  @RequestMapping(value = "/" , method = RequestMethod.POST)
  public Route generateRoute(@RequestBody GenerateRoute generateRoute ){
    return routesService.findBestRoute( generateRoute.getStops() , generateRoute.getVehicle()  );
  }

}
