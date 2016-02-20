package br.com.trix.services;

import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import br.com.trix.repositories.RouteRepository;
import br.com.trix.repositories.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Lazy(true)
@Service
public class RoutesService  {

  @Autowired StopRepository stopRepository;
  @Autowired RouteRepository routeRepository;

  public Route findBestRoute(List<Stop> stops, Integer vehicleId ){
    saveAllStops(stops, vehicleId);
    List<Stop> orderedStops = orderByNearestsStops(stops, vehicleId);
    return null;
  }

  protected List<Stop> orderByNearestsStops(List<Stop> stops, Integer vehicleId) {
    List<Stop> orderedStops = new ArrayList<>(stops.size());
    Point statingPoint = new Point( 0.0 , 0.0 );
    for(int i = 0; i < stops.size() ; i++ ){
      List<Stop> nearestStops = stopRepository.findByVehicleIdAndPositionNear(vehicleId, statingPoint, new Distance(10));
      if(nearestStops.isEmpty()) {
        throw new StopsTooFarException("The distance between the stops are too far");
      }else {
        Stop nearestStop = nearestStops.get(0);
        orderedStops.add(nearestStop);
        statingPoint = nearestStop.getPoint();
        stopRepository.delete(nearestStop);
      }
    }
    return orderedStops;
  }

  protected void saveAllStops(List<Stop> stops, Integer vehicleId) {
    for( Stop s : stops ){
      s.setVehicleId( vehicleId  );
      stopRepository.save( s );
    }
  }


}
