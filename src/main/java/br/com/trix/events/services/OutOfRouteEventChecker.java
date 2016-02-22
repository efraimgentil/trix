package br.com.trix.events.services;

import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.GeoCommand;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Lazy(true)
@Service
public class OutOfRouteEventChecker implements EventChecker {

  @Autowired
  MongoTemplate mongoTemplate;


  @Override
  public void check(Vehicle vehicle, Position position) {
    double acceptableRange = new Distance(0.5, Metrics.KILOMETERS).getNormalizedValue(); //500 metros
    Query query = Query
         .query(Criteria.where("id").is(vehicle.getCurrentRoute()))
         .addCriteria( Criteria.where("vehicleId").is( vehicle.getId() ) )
         .addCriteria( Criteria.where("path").withinSphere(new Circle(position.toPoint(), acceptableRange) ));
    List<Route> routes = mongoTemplate.find(query, Route.class);


  }

}
