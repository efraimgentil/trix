package br.com.trix.models;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.geo.Point;
import static org.junit.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
public class VehicleTest {

  Vehicle vehicle;

  @Before
  public void setUp(){
    vehicle = new Vehicle();
  }

  @Test(expected = IllegalStateException.class)
  public void shouldThrowIllegalStateExceptionWhenTryingToGetPointFromAVehicleWithoutCurrentPosition(){
    vehicle.setCurrentPosition( null );

    vehicle.getPoint();
  }

  @Test
  public void shouldReturnThePointFromTheCurrentPositionFromVehicle(){
    Position p = new Position( 10.12938987 , 20.43213123);
    vehicle.setCurrentPosition( p );

    Point point = vehicle.getPoint();

    assertEquals("Point.x should be the same of the Position.lat", p.getLat() , point.getX() , 0.0);
    assertEquals("Point.y should be the same of the Position.lng", p.getLng() , point.getY() , 0.0);
  }

}
