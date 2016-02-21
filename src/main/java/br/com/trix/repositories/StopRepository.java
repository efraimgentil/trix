package br.com.trix.repositories;

import br.com.trix.models.Position;
import br.com.trix.models.Stop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public interface StopRepository extends MongoRepository<Stop, String> {

  Page<Stop> findByRouteIdAndPositionNear(String routeId , Point position , Distance distance , Pageable pageable);

}
