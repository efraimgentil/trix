package br.com.trix.repositories;

import br.com.trix.MongoTestConfig;
import br.com.trix.config.SpringConfig;
import br.com.trix.config.SpringDataMongoConfig;
import br.com.trix.models.Position;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@ContextConfiguration(classes = { SpringConfig.class, MongoTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class StopRepositoryIT {


  @Autowired
  private StopRepository stopRepository;

  @Before
  public void init(){
    Stop s = new Stop("Pos 1 1" , new Position(50.0 , 50.0));
    s.setRouteId("1");
    stopRepository.save(s);
    s = new Stop("Pos 2 1" , new Position(51.0 , 51.0));
    s.setRouteId("1");
    stopRepository.save( s );
    s = new Stop("Pos 3 1" , new Position(55.0 , 55.0));
    s.setRouteId("1");
    s = new Stop("Pos 4 1" , new Position(60.0 , 60.0));
    s.setRouteId("1");
    stopRepository.save(s);
    s = new Stop("Pos 1 2" , new Position(49.5 , 49.9));
    s.setRouteId("2");
    stopRepository.save(s);
    s = new Stop("Pos 2 2" , new Position(51.0 , 51.0));
    s.setRouteId("2");
    stopRepository.save(s);
    s = new Stop("Pos 3 2" , new Position(50.0 , 50.0));
    s.setRouteId("2");
    stopRepository.save(s);
  }

  @After
  public void destroyTest(){
    stopRepository.deleteAll();
  }

  @Test
  public void doesReturnTheNearestPoint(){
    Vehicle vehicle = new Vehicle();
    vehicle.setCurrentRoute("2");

    Page<Stop> byVehicleIdNear = stopRepository.findByRouteIdAndPositionNear(vehicle.getCurrentRoute(), new Point(49.0, 50.0), new Distance(100) , new PageRequest(0 , 1));

    assertFalse( byVehicleIdNear.getContent().isEmpty() );
    for( Stop s : byVehicleIdNear ) {
      assertEquals( vehicle.getCurrentRoute() , s.getRouteId() );
    }
  }



}
