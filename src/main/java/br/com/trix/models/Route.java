package br.com.trix.models;

import br.com.trix.models.vo.StopVO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Document(collection = "routes")
public class Route {

  @Id
  private String id;
  private String name;
  private Date routeDate;
  @Indexed
  private String vehicleId;
  private List<StopVO> stops;
  private List<Coordinate> path;

  public Route() {
  }

  public Route(String vehicleId, List<StopVO> stops, List<Coordinate> path) {
    this.name = "Route random name " + System.nanoTime();
    this.routeDate = new Date();
    this.vehicleId = vehicleId;
    this.stops = stops;
    this.path = path;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Route{");
    sb.append("id='").append(id).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", routeDate=").append(routeDate);
    sb.append(", vehicleId='").append(vehicleId).append('\'');
    sb.append(", stops=").append(stops);
    sb.append(", path=").append(path);
    sb.append('}');
    return sb.toString();
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Date getRouteDate() {
    return routeDate;
  }
  public void setRouteDate(Date routeDate) {
    this.routeDate = routeDate;
  }
  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }
  public List<StopVO> getStops() {
    return stops;
  }
  public void setStops(List<StopVO> stops) {
    this.stops = stops;
  }
  public List<Coordinate> getPath() {
    return path;
  }
  public void setPath(List<Coordinate> path) {
    this.path = path;
  }
}
