package br.com.trix.models;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.geo.Point;

import static org.junit.Assert.assertEquals;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class StopTest {


  Stop stop;

  @Before
  public void setUp(){
    stop = new Stop();
  }

  @Test(expected = IllegalStateException.class)
  public void shouldThrowIllegalStateExceptionWhenTryingToGetPointFromAStopWithoutPosition(){
    stop.setPosition(null);

    stop.getPoint();
  }

  @Test
  public void shouldReturnThePointFromTheStopPosition(){
    Position position = new Position( 10.12938987 , 20.43213123);
    stop.setPosition(position);

    Point point = stop.getPoint();

    assertEquals("point.x should be the same of the position.lat", position.getLat() , point.getX() , 0.0);
    assertEquals("point.y should be the same of the position.lng", position.getLng() , point.getY() , 0.0);
  }


}
