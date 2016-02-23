package br.com.trix.events.models.vo;

import br.com.trix.models.Position;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 22/02/16.
 */
public class EventRequest {

  private String vehicleId;
  private Position position;

  public EventRequest() {
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("EventRequest{");
    sb.append("vehicleId='").append(vehicleId).append('\'');
    sb.append(", position=").append(position);
    sb.append('}');
    return sb.toString();
  }

  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }
  public Position getPosition() {
    return position;
  }
  public void setPosition(Position position) {
    this.position = position;
  }

}
