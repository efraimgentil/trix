package br.com.trix.models.converters;

import br.com.trix.models.Position;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class PositionWriteConverterTest {

  PositionWriteConverter converter;

  @Before
  public void setUp(){
    converter = new PositionWriteConverter();
  }

  @Test
  public void shouldReturnAnValidPointDbObject(){
    Position position = new Position(10.0 , 20.0);

    DBObject converted = converter.convert(position);

    assertEquals("type should have the 'Point' value ", "Point", converted.get("type"));
    Double[] posArr = (Double[]) converted.get("coordinates");
    assertTrue("coordinates array should not be null",  posArr != null );
    assertEquals("position 0 should have the value 10.0", 10.0, posArr[0], 0.0);
    assertEquals("position 1 should have the value 20.0" , 20.0 , posArr[1], 0.0);
  }

  @Test
  public void shouldReturnNullIfAnNullReferenceIsPassedToConvert(){
    Position position = null;

    DBObject converted = converter.convert(position);

    assertNull("should be null", converted );
  }

}

