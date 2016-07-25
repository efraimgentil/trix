package br.com.trix.events.services;

import br.com.trix.events.models.Occurrence;
import br.com.trix.models.Position;
import br.com.trix.models.Stop;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.OccurrenceRepository;
import br.com.trix.repositories.StopRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public class NearStopEventCheckerTest {

  NearStopEventChecker checker;
  StopRepository mockedStopRepository;
  OccurrenceRepository mockedOccurrenceRepository;
  MessageSource messageSource;

  @Before
  public void setUp(){
    checker = new NearStopEventChecker();
    mockedStopRepository = mock(StopRepository.class);
    checker.stopRepository = mockedStopRepository;
    mockedOccurrenceRepository = mock(OccurrenceRepository.class);
    checker.occurrenceRepository = mockedOccurrenceRepository;
    messageSource = mock(MessageSource.class);
    checker.messageSource = messageSource;
  }

  @Test
  public void shouldReturnNewOccurrenceWithNextStopInfo(){
    String vehicleId = "mockedVehicleId";
    String currentRouteId = "mockedRouteId";
    Vehicle vehicle = mock(Vehicle.class);
    Position position = new Position( 0.0 , 0.0 );
    Stop stop = mock(Stop.class);
    when(vehicle.getId()).thenReturn( vehicleId );
    when(vehicle.getCurrentRoute()).thenReturn(currentRouteId );

    Occurrence occurrence = checker.messageOccurrence(vehicle, position, stop);

    assertTrue("Should return occurrence with filled logDate ", occurrence.getLogDate() != null );
    assertEquals("Should return occurrence with vahicleId ", vehicleId,  occurrence.getVehicleId()  );
    assertEquals("Should return occurrence with routeId equal vehicle.currentRouteId ", vehicleId,  occurrence.getVehicleId()  );
    assertEquals("Should return occurrence with current vehicle position", position, occurrence.getVehiclePosition() );
  }

  @Test(expected =  EventException.class )
  public void shouldThrowEventExceptionWhenDoesNotFindTheNearestStop(){
    Vehicle vehicle = mock(Vehicle.class);
    Position position = mock(Position.class);
    Stop stop = mock(Stop.class);
    Page<Stop> mockedPage = mock(Page.class);
    when( mockedStopRepository.findByRouteIdAndPositionNear(
            anyString(), any(Point.class) , any(PageRequest.class) )
    ).thenReturn(mockedPage);
    when(mockedPage.getContent()).thenReturn(Collections.emptyList());

    checker.check(vehicle, position);
  }

  @Test
  public void shouldSaveTheOccurrenceOfTheNearestStop(){
    Vehicle vehicle = mock(Vehicle.class);
    Position position = mock(Position.class);
    Stop stop = mock(Stop.class);
    Page<Stop> mockedPage = mock(Page.class);
    when( mockedStopRepository.findByRouteIdAndPositionNear(
                    anyString(), any(Point.class), any(PageRequest.class) )
    ).thenReturn(mockedPage);
    when(mockedPage.getContent()).thenReturn(Arrays.asList(new Stop()));

    checker.check(vehicle, position);

    verify( mockedOccurrenceRepository , times(1) ).save( any(Occurrence.class) );
  }


}

