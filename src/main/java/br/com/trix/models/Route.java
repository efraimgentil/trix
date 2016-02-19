package br.com.trix.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Document(collection = "routes")
public class Route {

  @Id
  private String id;
  private Date routeDate;
  private Integer vehicleId;
  private List<Stop> stops;

  public Route() {
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
  public Integer getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(Integer vehicleId) {
    this.vehicleId = vehicleId;
  }
  public List<Stop> getStops() {
    return stops;
  }
  public void setStops(List<Stop> stops) {
    this.stops = stops;
  }

}
