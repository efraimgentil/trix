package br.com.trix.models;

import java.io.Serializable;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public class Position implements Serializable{

  private double lat;
  private double lng;

  public Position() {
  }

  public Position(double lat, double lng) {
    super();
    this.lat = lat;
    this.lng = lng;
  }

  @Override
  public String toString() {
    return "Location [lat=" + lat + ", lng=" + lng + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(lat);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(lng);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Position other = (Position) obj;
    if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
      return false;
    if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
      return false;
    return true;
  }

  public double getLat() {
    return lat;
  }
  public void setLat(double lat) {
    this.lat = lat;
  }
  public double getLng() {
    return lng;
  }
  public void setLng(double lng) {
    this.lng = lng;
  }

}
