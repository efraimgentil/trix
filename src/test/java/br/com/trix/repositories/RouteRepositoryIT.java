package br.com.trix.repositories;

import br.com.trix.MongoTestConfig;
import br.com.trix.config.SpringConfig;
import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@ContextConfiguration(classes = { SpringConfig.class, MongoTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class RouteRepositoryIT {

  @Autowired
  RouteRepository routeRepository;
  @Autowired
  MongoTemplate mongoTemplate;

  /*@Test
  public void shouldReturnRouteNearThePositionForTheVehicle(){
    Vehicle vehicle = new Vehicle();
    vehicle.setId("56c9cd41c830f22b6a9b4660");
    vehicle.setCurrentRoute("56ca33c4c830eed19c433859");
    Position position = new Position( -3.7450951639098378,-38.51478321477771 );
    Distance distance = new Distance( 0.5 , Metrics.KILOMETERS );

    List<Route> routeNearPosition = routeRepository.findRouteNearPosition(vehicle, position, distance);
    for(Route r : routeNearPosition)
      System.out.println(r);
    assertFalse( routeNearPosition.isEmpty() );
  }

  @Test
  public void t(){
    Query query = new Query();
    NearQuery near = NearQuery.near(-3.7450951639098378, -38.51478321477771)
            .maxDistance( 0.5 , Metrics.KILOMETERS).spherical(true).query(query);
    GeoResults<Route> routes = mongoTemplate.geoNear(near, Route.class, "routes");
    System.out.println(routes.getContent().isEmpty() );
  }*/

}
