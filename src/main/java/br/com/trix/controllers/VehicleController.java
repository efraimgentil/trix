package br.com.trix.controllers;

import br.com.trix.models.Route;
import br.com.trix.models.Vehicle;
import br.com.trix.models.vo.GenerateRoute;
import br.com.trix.repositories.RouteRepository;
import br.com.trix.repositories.VehicleRepository;
import br.com.trix.services.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

  @Autowired VehicleRepository vehicleRepository;

  @RequestMapping(value = "/" , method = RequestMethod.GET)
  public List<Vehicle> vehicles(){
    return vehicleRepository.findAll();
  }

  @RequestMapping(value = "/{id}/" , method = RequestMethod.GET)
  public Vehicle vehicle(@PathVariable("id") String id){
    return vehicleRepository.findOne(id);
  }

  @RequestMapping(value = "/" , method = RequestMethod.POST)
  public Vehicle vehicle(@RequestBody Vehicle vehicle){
    return vehicleRepository.save( vehicle );
  }

}
