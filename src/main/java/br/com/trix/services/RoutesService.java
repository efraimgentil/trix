package br.com.trix.services;

import br.com.trix.config.SpringConfig;
import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.RouteRepository;
import br.com.trix.repositories.StopRepository;
import br.com.trix.repositories.VehicleRepository;
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

  public Route findBestRoute(List<Stop> stops, String vehicleId ){
    Vehicle vehicle = vehicleRepository.findOne( vehicleId );
    if(vehicle == null) throw new VehicleDoesnotExistException();
    JsonNode jsonNode = callGoogleService(vehicle, stops);
    List<Stop> orderedStops = orderStops(jsonNode, stops);
    List<Position> path = readPathFromJson(jsonNode);
    Route route = routeRepository.save(new Route(vehicleId, orderedStops, path));
    saveStops( route );
    vehicle.setCurrentRoute( route.getId() );
    vehicleRepository.save( vehicle );
    return route;
  }

  public void saveStops(Route route ){
    for (Stop s : route.getStops() ){
      s.setRouteId( route.getId() );
      stopRepository.save(s);
    }
  }

  protected List<Position> readPathFromJson(JsonNode result) {
    List<Position> positions = new ArrayList<>();
    for(JsonNode legs : result.get("legs")){
      for(JsonNode step : legs.get("steps")) {
        JsonNode s =  step.get("start_location");
        positions.add(new Position(s.get("lat").asDouble() , s.get("lng").asDouble() ));
        s =  step.get("end_location");
        positions.add(new Position( s.get("lat").asDouble()  , s.get("lng").asDouble() ));
      }
    }
    return positions;
  }

  protected JsonNode callGoogleService(Vehicle vehicle , List<Stop> stops ){
    String uri = mountApiUri( vehicle , stops );
    JsonNode result = new RestTemplate().getForObject( uri , JsonNode.class);
    System.out.println(result);
    return validateResultAndReturn(result);
  }

  private JsonNode validateResultAndReturn(JsonNode result) {
    String status = result.get("status").asText();
    if("NOT_FOUND".equals(status)){
      throw new RouteNotFoundException();
    }
    return result.get("routes").get(0);
  }

  protected String mountApiUri(Vehicle vehicle , List<Stop> stops ){
    String uri = googleDirections;
    uri += "?key="  + googleApiKey;
    uri += "&origin="  + vehicle.getLatLng();
    uri += "&destination="  + vehicle.getLatLng();
    String waipoints = "optimize:true";
    for (Stop stop : stops) {
      waipoints += "|" + stop.getLatLng();
    }
    uri += "&waypoints="+ waipoints;
    return uri;
  }

  protected List<Stop> orderStops(JsonNode routeNode, List<Stop> stops) {
    final List<Stop> orderedStops = new ArrayList<>();
    for (JsonNode n : routeNode.get("waypoint_order")) {
      orderedStops.add(stops.get(n.asInt()));
    }
    return orderedStops;
  }

}
