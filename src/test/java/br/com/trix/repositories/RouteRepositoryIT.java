package br.com.trix.repositories;

import br.com.trix.MongoTestConfig;
import br.com.trix.config.SpringConfig;
import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

  @Test
  public void t(){
    Query query = new Query();
    /*Criteria.where("position").near(new Point(-3.7716079842618810, -38.48313859663904) )*/
    query.limit(1)
          .fields().include("stops").exclude("_id");
                    //.elemMatch("stops", Criteria.where("position").near(new Point(-3.7452985764579325, -38.52330174297093)));
    /*System.out.println( mongoTemplate.find(query, Route.class, "routes"));*/
    NearQuery near = NearQuery.near(-3.7490884647584791, -38.52255072444676).spherical(true).query(query);
    GeoResults<Route> geoResults = mongoTemplate.geoNear(near, Route.class, "routes");
    System.out.println( geoResults );
  }

}
