package br.com.trix.events.models;

import br.com.trix.models.Position;
import br.com.trix.models.Vehicle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Document(collection =  "occurences")
public class Occurrence {

  @Id
  private String id;

  private OccurrenceType type;
  @Indexed
  private Date logDate;
  @Indexed
  private String vehicleId;
  private Position vehiclePosition;
  @Indexed
  private String routeId;
  private String message;

  public Occurrence() {
  }

  public static Occurrence newOccurrence(Vehicle vehicle , Position position, OccurrenceType type){
    Occurrence occurrence = new Occurrence();
    occurrence.setLogDate( new Date() );
    occurrence.setVehiclePosition(position);
    occurrence.setVehicleId(vehicle.getId());
    occurrence.setRouteId(vehicle.getCurrentRoute());
    occurrence.setType(type);
    return occurrence;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Occurrence{");
    sb.append("id='").append(id).append('\'');
    sb.append(", logDate=").append(logDate);
    sb.append(", vehicleId='").append(vehicleId).append('\'');
    sb.append(", message='").append(message).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Date getLogDate() {
    return logDate;
  }
  public void setLogDate(Date logDate) {
    this.logDate = logDate;
  }
  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public String getRouteId() {
    return routeId;
  }
  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }
  public Position getVehiclePosition() {
    return vehiclePosition;
  }
  public void setVehiclePosition(Position vehiclePosition) {
    this.vehiclePosition = vehiclePosition;
  }
  public OccurrenceType getType() {
    return type;
  }
  public void setType(OccurrenceType type) {
    this.type = type;
  }


}
