package br.com.trix.models;

import br.com.trix.models.vo.StopVO;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Document(collection = "stops")
@CompoundIndexes(value = {
  @CompoundIndex( name = "position__2dsphere", def = "{ 'position': '2dsphere' }" )
})
public class Stop extends StopVO implements Serializable {

  @Id
  private String id;
  @Indexed
  private String routeId;

  public Stop() {
  }

  public Stop(String name, Position position) {
    setName(name);
    setPosition( position );
  }

  public Stop(StopVO s) {
    setName(s.getName());
    setPosition( s.getPosition());
  }

  public Point getPoint(){
    if(getPosition() == null)
      throw new IllegalStateException("No current possition set for the Stop");
    return new Point( getPosition().getLat() , getPosition().getLng() );
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Stop{");
    sb.append("id='").append(id).append('\'');
    sb.append(", name='").append(getName()).append('\'');
    sb.append(", position=").append(getPosition());
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stop stop = (Stop) o;
    if (getPosition() != null ? !getPosition().equals(stop.getPosition()) : stop.getPosition() != null) return false;
    return true;
  }
  @Override
  public int hashCode() {
    return getPosition() != null ? getPosition().hashCode() : 0;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getRouteId() {
    return routeId;
  }
  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }

}
