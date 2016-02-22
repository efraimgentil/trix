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
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
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

  @Autowired
  Mongo mongo;

  @Qualifier(SpringDataMongoConfig.MONGO_DB)
  @Autowired
  String mongoDbName;

  public List<Route> findRouteNearPosition(Vehicle vehicle, Position position , Distance acceptableDistance) {
    BigDecimal b = new BigDecimal(0.5).divide( new BigDecimal( Metrics.KILOMETERS.getMultiplier() ) , MathContext.DECIMAL32 );



    String command = "{ path: {"
     +  "$geoWithin: {"
     +   "$centerSphere: [ [ "+position.getLat()+"," + position.getLng()+ "]," + b.toString() + " ]"
     + "}"
     + "} }";
    BasicDBList centerSphere = new BasicDBList();
    BasicDBList coodenates = new BasicDBList();
    coodenates.add(  position.getLat() );
    coodenates.add(  position.getLng() );
    centerSphere.add( coodenates );
    centerSphere.add( b );
    DBObject o = new BasicDBObject("path" , new BasicDBObject("$geoWithin" , new BasicDBObject("$centerSphere" , centerSphere )) );
    DBCollection collection = mongo.getDB( mongoDbName ).getCollection("routes");
    DBCursor dbObjects = collection.find(o);
    //List<Route> routes = mongoTemplate.find(new BasicQuery(o), Route.class, "routes");

    Query query = Query
            .query(Criteria.where("_id").is(  vehicle.getCurrentRoute() ))
            .addCriteria( Criteria.where("path").withinSphere(new Circle(position.toPoint(), b.doubleValue()) ));
    return mongoTemplate.find(query, Route.class , "routes");
  }

}
