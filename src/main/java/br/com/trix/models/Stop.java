package br.com.trix.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Document(collection = "stops")
@CompoundIndex(collection = "stops", name = "position_2dsphere", def = "{ 'position': '2dsphere' } "  )
public class Stop implements Serializable {

  @Id
  private String id;
  private String name;
  @Indexed
  private String routeId;
  private Position position;

  public Stop() {
  }

  public Stop(String name, Position position) {
    this.name = name;
    this.position = position;
  }

  public Point getPoint(){
    return new Point( position.getLat() , position.getLng() );
  }

  public String getLatLng(){
    return String.valueOf(position.getLat()) + "," + String.valueOf(position.getLng());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Stop{");
    sb.append("id='").append(id).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", position=").append(position);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stop stop = (Stop) o;
    if (position != null ? !position.equals(stop.position) : stop.position != null) return false;
    return true;
  }
  @Override
  public int hashCode() {
    return position != null ? position.hashCode() : 0;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Position getPosition() {
    return position;
  }
  public void setPosition(Position position) {
    this.position = position;
  }
  public String getRouteId() {
    return routeId;
  }
  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }

}
