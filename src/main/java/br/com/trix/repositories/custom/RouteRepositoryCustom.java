package br.com.trix.repositories.custom;

import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Vehicle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public interface RouteRepositoryCustom {


  GeoResults<Route> findRouteNearPosition(Vehicle vehicle , Position position , double acceptableRange );

}
