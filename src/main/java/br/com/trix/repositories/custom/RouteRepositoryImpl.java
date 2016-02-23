package br.com.trix.repositories.custom;

import br.com.trix.config.SpringDataMongoConfig;
import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Vehicle;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public class RouteRepositoryImpl implements RouteRepositoryCustom {

  @Autowired
  MongoTemplate mongoTemplate;

  public GeoResults<Route> findRouteNearPosition(Vehicle vehicle, Position position, double acceptableRange ) {
    Query query = new Query();
    query.addCriteria( Criteria.where("vehicleId").is( vehicle.getId() ) );
    NearQuery near = NearQuery.near(  position.getLat() , position.getLng()  )
            .maxDistance( acceptableRange , Metrics.KILOMETERS)
            .spherical(true)
            .query(query);
    return mongoTemplate.geoNear( near , Route.class , "routes");
  }

}
