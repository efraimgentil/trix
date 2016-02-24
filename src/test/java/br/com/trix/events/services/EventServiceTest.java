package br.com.trix.events.services;

import br.com.trix.events.models.vo.EventRequest;
import br.com.trix.events.services.exceptions.EventWithoutPositionException;
import br.com.trix.models.Position;
import br.com.trix.models.Vehicle;
import br.com.trix.repositories.VehicleRepository;
import br.com.trix.services.exceptions.VehicleDoesNotExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class EventServiceTest {

  EventService eventService;
  VehicleRepository vehicleRepository;

  @Before
  public void setUp(){
    eventService = new EventService();
    vehicleRepository = mock(VehicleRepository.class);
    eventService.vehicleRepository = vehicleRepository;
  }

  @Test(expected = VehicleDoesNotExistException.class )
  public void shouldThrowVehicleNotFoundWhenTheVehicleIsNull(){
    Vehicle vehicle = null;
    EventRequest eventRequest = null;

    eventService.validateEventRequest(  eventRequest , vehicle);
  }

  @Test(expected = EventWithoutPositionException.class )
  public void shouldThrowDefaultValidationExceptionWhenThereIsNoPositionInTheEventRequest(){
    Vehicle vehicle = new Vehicle();
    EventRequest eventRequest = mock(EventRequest.class);

    when(eventRequest.getPosition() ).thenReturn( null );

    eventService.validateEventRequest(  eventRequest , vehicle);
  }

  @Test
  public void shouldValidateBeforeCallEventCheckers(){
    EventRequest eventRequest = mock(EventRequest.class);
    EventChecker mockChecker1 = mock(EventChecker.class);
    EventChecker mockChecker2 = mock(EventChecker.class);
    eventService.setCheckers(Arrays.asList( mockChecker1 , mockChecker2 ) );
    eventService = spy(eventService );

    when(vehicleRepository.findOne( anyString() ) ).thenReturn( new Vehicle() );
    when(eventRequest.getPosition() ).thenReturn( new Position() );

    InOrder inOrder = inOrder(eventService, mockChecker1, mockChecker2);

    eventService.checkEventOccurrence( eventRequest );

    inOrder.verify( eventService , times(1) ).validateEventRequest(eq(eventRequest), any(Vehicle.class));
    inOrder.verify( mockChecker1 , times(1) ).check(any(Vehicle.class), any(Position.class) );
    inOrder.verify( mockChecker2 , times(1) ).check(any(Vehicle.class), any(Position.class) );
  }

}
