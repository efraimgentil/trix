package br.com.trix.services;

import br.com.trix.MongoTestConfig;
import br.com.trix.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@ContextConfiguration(classes = { SpringConfig.class, MongoTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class RoutesServiceIT {


  @Autowired
  RoutesService routesService;


}
