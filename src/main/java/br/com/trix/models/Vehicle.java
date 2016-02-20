package br.com.trix.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@Document(collection = "vehicles")
public class Vehicle implements Serializable {

  @Id
  private String id;
  private Position currentPosition;

  public Vehicle() {
  }

  public Point getPoint() {
      if(currentPosition == null)
        throw new IllegalStateException("No current possition set");
      return new Point(currentPosition.getLat() , currentPosition.getLng());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Vehicle{");
    sb.append("id='").append(id).append('\'');
    sb.append(", currentPosition=").append(currentPosition);
    sb.append('}');
    return sb.toString();
  }

  public void setCurrentPosition(Position currentPosition) {
    this.currentPosition = currentPosition;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Position getCurrentPosition() {
    return currentPosition;
  }


}
