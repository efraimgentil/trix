package br.com.trix.services;

import br.com.trix.config.SpringConfig;
import br.com.trix.models.*;
import br.com.trix.models.vo.StopVO;
import br.com.trix.repositories.RouteRepository;
import br.com.trix.repositories.StopRepository;
import br.com.trix.repositories.VehicleRepository;
import br.com.trix.services.exceptions.NoWayPointOrderException;
import br.com.trix.services.exceptions.RouteCreationException;
import br.com.trix.services.exceptions.RouteDoesNotExistException;
import br.com.trix.services.exceptions.VehicleDoesNotExistException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Lazy(true)
@Service
public class RoutesService  {

  private String googleDirections = "https://maps.googleapis.com/maps/api/directions/json";

  @Autowired @Qualifier(SpringConfig.GOOGLE_KEY) String googleApiKey;
  @Autowired RouteRepository routeRepository;
  @Autowired VehicleRepository vehicleRepository;
  @Autowired StopRepository stopRepository;

  public Route findBestRoute(List<StopVO> stops, String vehicleId ){
    Vehicle vehicle = vehicleRepository.findOne( vehicleId );
    if(vehicle == null) throw new VehicleDoesNotExistException();
    JsonNode jsonNode = callGoogleService(vehicle, stops);
    List<StopVO> orderedStops = orderStops(jsonNode, stops);
    List<Coordinate> path = readPathFromJson(jsonNode);
    Route route = routeRepository.save(new Route(vehicleId, orderedStops, path));
    saveStops( route );
    vehicle.setCurrentRoute( route.getId() );
    vehicleRepository.save( vehicle );
    return route;
  }

  public void saveStops(Route route ){
    for (StopVO s : route.getStops() ){
      Stop stop = new Stop( s );
      stop.setRouteId(route.getId());
      stopRepository.save(stop);
    }
  }

  protected List<Coordinate> readPathFromJson(JsonNode result) {
    List<Coordinate> positions = new ArrayList<>();
    for(JsonNode legs : result.get("legs")){
      for(JsonNode step : legs.get("steps")) {
        JsonNode s =  step.get("start_location");
        positions.add( new Coordinate(new Position(s.get("lat").asDouble() , s.get("lng").asDouble() ) ));
        s =  step.get("end_location");
        positions.add( new Coordinate(new Position( s.get("lat").asDouble()  , s.get("lng").asDouble() ) ));
      }
    }
    return positions;
  }

  protected JsonNode callGoogleService(Vehicle vehicle , List<StopVO> stops ){
    String uri = mountApiUri( vehicle , stops );
    JsonNode result = new RestTemplate().getForObject( uri , JsonNode.class);
    return validateResultAndReturn(result);
  }

  protected JsonNode validateResultAndReturn(JsonNode result) {
    String status = result.get("status").asText();
    if("NOT_FOUND".equals(status)){
      throw new RouteCreationException();
    }
    return result.get("routes").get(0);
  }

  protected String mountApiUri(Vehicle vehicle , List<StopVO> stops ){
    String uri = googleDirections;
    uri += "?key="  + googleApiKey;
    uri += "&origin="  + vehicle.getLatLng();
    uri += "&destination="  + vehicle.getLatLng();
    String waipoints = "optimize:true";
    for (StopVO stop : stops) {
      waipoints += "|" + stop.getPosition().getLatLng();
    }
    uri += "&waypoints="+ waipoints;
    return uri;
  }

  protected List<StopVO> orderStops(JsonNode routeNode, List<StopVO> stops) {
    final List<StopVO> orderedStops = new ArrayList<>();
    if( !routeNode.has("waypoint_order") ) throw new NoWayPointOrderException();
    for (JsonNode n : routeNode.get("waypoint_order")) {
      orderedStops.add(stops.get(n.asInt()));
    }
    return orderedStops;
  }

}
