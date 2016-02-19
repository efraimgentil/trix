package br.com.trix.controllers;

import br.com.trix.models.Route;
import br.com.trix.repositories.RouteRepository;
import br.com.trix.repositories.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@RestController
@RequestMapping("/rotas")
public class RoutesController {

  @Autowired
  protected RouteRepository routeRepository;

  @RequestMapping(value = "/" , method = RequestMethod.GET)
  public List<Route> rotas(){
    return routeRepository.findAll();
  }

}
