package br.com.trix.controllers;

import br.com.trix.MongoTestConfig;
import br.com.trix.config.SpringConfig;
import br.com.trix.config.SpringWebConfig;
import br.com.trix.models.vo.ErrorMessage;
import br.com.trix.services.exceptions.DefaultValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
@ContextConfiguration(classes = { SpringConfig.class, MongoTestConfig.class, SpringWebConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TrixControllerExceptionHandlerIT {

  @Autowired
  TrixControllerExceptionHandler controller;
  @Autowired
  MessageSource messageSource;

  @Test
  public void test(){

    System.out.println( messageSource.getMessage("event.without_position.description" , new Object[]{} , null ) );

    /*DefaultValidationException e = new DefaultValidationException("asdas" , "adasdasd");

    ErrorMessage errorMessage = controller.handleTypeMismatchException(e);*/
  }

}
