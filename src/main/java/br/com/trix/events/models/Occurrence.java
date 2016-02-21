package br.com.trix.events.models;

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
  @Indexed
  private Date logDate;
  @Indexed
  private String vehicleId;
  private String message;

  public Occurrence() {
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
}
