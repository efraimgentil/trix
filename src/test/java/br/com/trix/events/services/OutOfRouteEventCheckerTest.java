package br.com.trix.events.services;

import br.com.trix.events.models.Occurrence;
import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.OccurrenceRepository;
import br.com.trix.repositories.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class OutOfRouteEventCheckerTest {

  OutOfRouteEventChecker checker;
  RouteRepository routeRepository;
  OccurrenceRepository occurrenceRepository;

  @Before
  public void setUp(){
    checker = new OutOfRouteEventChecker();
    routeRepository= mock(RouteRepository.class);
    checker.routeRepository = routeRepository;
    occurrenceRepository = mock(OccurrenceRepository.class);
    checker.occurrenceRepository = occurrenceRepository;
  }

  @Test
  public void shouldSaveAnOccurrenceWhenThereIsNoRouteInTheRangeOf500Meters(){
    Vehicle vehicle = mock(Vehicle.class);
    Position position = mock(Position.class);
    GeoResults<Route> result = mock(GeoResults.class);
    List<GeoResult<Route>> content = mock(List.class);

    when( routeRepository.findRouteNearPosition( eq(vehicle) ,  eq( position)  , any(double.class) ) ).thenReturn(result);
    when( result.getContent() ).thenReturn(content);
    when( content.isEmpty() ).thenReturn( true );

    checker.check( vehicle , position );

    verify( occurrenceRepository , times(1) ).save( any(Occurrence.class) );
  }

  @Test
  public void shouldNotSaveAnOccurrenceWhenThereIsARouteInTheRangeOf500Meters(){
    Vehicle vehicle = mock(Vehicle.class);
    Position position = mock(Position.class);
    GeoResults<Route> result = mock(GeoResults.class);
    List<GeoResult<Route>> content = mock(List.class);

    when( routeRepository.findRouteNearPosition( eq(vehicle) ,  eq( position)  , any(double.class) ) ).thenReturn(result);
    when( result.getContent() ).thenReturn(content);
    when( content.isEmpty() ).thenReturn( false );

    checker.check( vehicle , position );

    verify( occurrenceRepository , never() ).save( any(Occurrence.class) );
  }



}
