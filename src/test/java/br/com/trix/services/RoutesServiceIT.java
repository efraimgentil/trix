package br.com.trix.services;

import br.com.trix.MongoTestConfig;
import br.com.trix.config.SpringConfig;
import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.VehicleRepository;
import br.com.trix.services.exceptions.RouteNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@ContextConfiguration(classes = { SpringConfig.class, MongoTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class RoutesServiceIT {

  @Autowired
  RoutesService routesService;
  @Autowired
  VehicleRepository vehicleRepository;

  @Before
  public void setUp(){

  }

  @After
  public void destroy(){
    vehicleRepository.deleteAll();
  }

 /* @Test
  public void shouldReturnTheOrderedStops(){
    Vehicle vehicle = new Vehicle();
    vehicle.setCurrentPosition(new Position( -3.7452985764579325 ,-38.52330174297094 ) );
    vehicleRepository.save(vehicle);
    Stop s1 = new Stop("Casa" , new Position(-3.7490884647584792 , -38.52255072444677 ));
    Stop s2 = new Stop("Iguatemi" , new Position( -3.7452985764579325 ,-38.52330174297094  ));
    Stop s3 = new Stop("Fa7" , new Position( -3.7716079842618813 , -38.48313859663904 ));
    Stop s4 = new Stop("Casa do Ze" , new Position(-3.7560044332605873 , -38.5133021324873 ));
    List<Stop> stops = Arrays.asList(s2, s3, s1,  s4);
    routesService.saveAllStops(stops , vehicle );

    List<Stop> orderedStops = routesService.orderByNearestsStops(stops, vehicle);

    assertEquals( s1 , orderedStops.get(0) );
    assertEquals( s2 , orderedStops.get(2) );
    assertEquals( s3 , orderedStops.get(3) );
    assertEquals( s4 , orderedStops.get(1) );
  }*/

  @Test
  public void t() throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyDS-yhi_2dNq2N75F6PIwEifqbTXmY0VsY&origin=-3.748745876660633,-38.52259363979101&destination=-3.748745876660633,-38.52259363979101&waypoints=optimize:true|-3.7462406971167876,-38.52192811667919|-3.7534350395905807,-38.51896695792675|-3.7469258751406755,-38.51265840232372|-3.7433715083156693,-38.51776532828808";
    String forObject = restTemplate.getForObject(url, String.class);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(forObject);

    JsonNode routes = jsonNode.get("routes");
    JsonNode route = routes.get(0);
    System.out.println(route.get("waypoint_order"));
    route.get("waypoint_order").forEach((n) -> {
      System.out.println(n);
    });
    route.get("legs").forEach((n) -> {
      //System.out.println( n.get("start_location") );
      n.get("steps").forEach( (step) -> {
        System.out.println( step.get("start_location") );
        System.out.println(step.get("end_location"));
      });
      //System.out.println( n.get("end_location") );
    });
  }


  @Test(expected =  RouteNotFoundException.class)
  public void shouldThrowRouteNotFoundWhenTheResponseIsNotValid(){
    Vehicle vehicle = new Vehicle();
    vehicle.setCurrentPosition( new Position(-3.748745876660633,-38.52259363979101));

    routesService.callGoogleService(vehicle, Collections.emptyList() );
  }

  @Test
  public void teste(){
    Vehicle vehicle = new Vehicle();
    vehicle.setCurrentPosition( new Position(-3.748745876660633,-38.52259363979101));
    vehicle = vehicleRepository.save(vehicle);

    Stop s1 = new Stop("Casa" , new Position(-3.7490884647584792 , -38.52255072444677 ));
    Stop s2 = new Stop("Iguatemi" , new Position( -3.7452985764579325 ,-38.52330174297094  ));
    Stop s3 = new Stop("Fa7" , new Position( -3.7716079842618813 , -38.48313859663904 ));
    Stop s4 = new Stop("Casa do Ze" , new Position(-3.7560044332605873 , -38.5133021324873 ));
    List<Stop> stops = Arrays.asList(s2, s3, s1,  s4);

    Route bestRoute = routesService.findBestRoute(stops, vehicle.getId());

    System.out.println( bestRoute );
  }


}
