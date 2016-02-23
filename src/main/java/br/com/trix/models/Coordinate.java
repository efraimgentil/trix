package br.com.trix.models;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class Coordinate {

  @GeoSpatialIndexed(name = "position_2dsphere" , type = GeoSpatialIndexType.GEO_2DSPHERE)
  private Position position;

  public Coordinate() {
  }
  public Coordinate(Position position) {
    this.position = position;
  }
  public Position getPosition() {
    return position;
  }
  public void setPosition(Position position) {
    this.position = position;
  }
}
