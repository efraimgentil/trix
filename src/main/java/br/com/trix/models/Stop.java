package br.com.trix.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Document(collection = "stops")
public class Stop implements Serializable {

  @Id
  private String id;
  private String nome;
  private Integer vehicleId;
  private Position position;
  private Point point;

  public Stop() {
  }

  public Stop(String nome, Position position) {
    this.nome = nome;
    setPosition( position );
  }

  public void setPosition(Position position) {
    this.position = position;
    if(position != null){
      point = new Point( position.getLat() , position.getLng() );
    }
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Stop{");
    sb.append("id='").append(id).append('\'');
    sb.append(", nome='").append(nome).append('\'');
    sb.append(", position=").append(position);
    sb.append(", point=").append(point);
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
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public Position getPosition() {
    return position;
  }
  public Point getPoint() {
    return new Point(point);
  }

  public Integer getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Integer vehicleId) {
    this.vehicleId = vehicleId;
  }
}
