package br.com.trix.models.converters;


import br.com.trix.models.Position;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class PositionReadConverterTest{

  PositionReadConverter converter;

  @Before
  public void setUp(){
    converter = new PositionReadConverter();
  }

  @Test
  public void shouldReturnAnPositionReadingTheInformationFromAnDbObject(){
    double expectedX = 0.01;
    double expectedY = 30.00;
    Position expectedPosition = new Position( expectedX , expectedY );

    DBObject dbo = mock( DBObject.class );
    BasicDBList dbList = mock( BasicDBList.class );
    when( dbo.get("coordinates") ).thenReturn( dbList );
    when( dbList.isEmpty() ).thenReturn(false);
    when( dbList.size() ).thenReturn(2);
    when( dbList.get(0) ).thenReturn(  expectedX );
    when( dbList.get(1) ).thenReturn(  expectedY );

    Position converted = converter.convert(dbo);

    assertEquals( expectedPosition , converted );
  }

  @Test
  public void shouldReturnNullWhenDBObjectIsNull(){
    Position converted = converter.convert( null );

    assertNull("Converted object should be null", converted);
  }

  @Test
  public void shouldReturnNullWhenDBObjectDoesNotHaveCoodinatesAttribute(){
    DBObject dbo = mock( DBObject.class );
    when( dbo.get("coordinates") ).thenReturn( null );

    Position converted = converter.convert( dbo );

    assertNull("Converted object should be null", converted );
  }

  @Test
  public void shouldReturnNullWhenDBObjectContainsCoodinatesAttributeButItDoesNotHaveTwoPositions(){
    DBObject dbo = mock( DBObject.class );
    BasicDBList dbList = mock( BasicDBList.class );
    when( dbo.get("coordinates") ).thenReturn( dbList );
    when( dbList.size() ).thenReturn( 1 );

    Position converted = converter.convert( dbo );

    assertNull("Converted object should be null", converted );
  }



}
