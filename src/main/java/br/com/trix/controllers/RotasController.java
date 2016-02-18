package br.com.trix.controllers;

import br.com.trix.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@RestController
@RequestMapping("/rotas")
public class RotasController {

  @Autowired
  protected ParadaRepository paradaRepository;

  @RequestMapping(value = "/" , method = RequestMethod.GET)
  public String rotas(){
    System.out.println( paradaRepository );
    System.out.println( paradaRepository.findAll() );

    return "LOLEE";
  }

}
