package br.com.trix.models.vo;

import br.com.trix.models.Stop;

import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public class GenerateRoute {

  private String vehicle;
  private List<StopVO> stops;

  public GenerateRoute() {
  }

  public String getVehicle() {
    return vehicle;
  }

  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

  public List<StopVO> getStops() {
    return stops;
  }

  public void setStops(List<StopVO> stops) {
    this.stops = stops;
  }
}
