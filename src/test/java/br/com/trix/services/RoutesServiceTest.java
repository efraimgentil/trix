package br.com.trix.services;


import br.com.trix.repositories.RouteRepository;
import org.junit.Before;

import static org.mockito.Mockito.mock;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public class RoutesServiceTest {

  RoutesService routesService;
  RouteRepository routeRepository;

  @Before
  public void setUp(){
    routesService = new RoutesService();
    routeRepository = mock(RouteRepository.class);
    routesService.routeRepository = routeRepository;
  }

 /* @Test
  public void shouldSaveAllStopForTheGivenVehicleId(){
    Stop s1 = mock(Stop.class);
    Stop s2 = mock(Stop.class);
    Stop s3 = mock(Stop.class);
    List<Stop> stops = Arrays.asList(s1,s2,s3);
    String vehicleId = "1";
    Vehicle v = new Vehicle();
    v.setId( vehicleId);

    routesService.saveAllStops(stops, v );

    InOrder inOrder = inOrder(stopRepository, s1, s2, s3);
    inOrder.verify(s1, times(1)).setVehicleId(vehicleId);
    inOrder.verify( stopRepository ).save( s1 );
    inOrder.verify(s2 , times(1) ).setVehicleId(vehicleId);
    inOrder.verify( stopRepository ).save( s2 );
    inOrder.verify(s3 , times(1) ).setVehicleId(vehicleId);
    inOrder.verify( stopRepository ).save(s3);
  }

  @Test(expected = StopsTooFarException.class )
  public void shouldThrowAnStopsTooFarExceptionIfDontFoundANearStop(){
    Stop s1 = mock(Stop.class);
    List<Stop> stops = Arrays.asList(s1);
    String vehicleId = "1";
    Vehicle v = new Vehicle();
    v.setId( vehicleId);

    when(stopRepository.findByVehicleIdAndPositionNear(anyString() , any(Point.class), any(Distance.class)))
            .thenReturn( Collections.emptyList() );

    routesService.orderByNearestsStops(  stops , v  );
  }

  @Test
  public void shouldFindTheNearestStopEqualsTheGivenAmountOfStops(){
    Stop s1 = new Stop("S1" , new Position(1.11111 , 1.21) );
    Stop s2 = new Stop("S2" , new Position(1.10000 , 1.1) );;
    Stop s3 = new Stop("S3" , new Position(2.11111 , 1.21) );;
    List<Stop> stops = Arrays.asList(s1,s2,s3);
    Vehicle vehicle = mock(Vehicle.class);

    when( vehicle.getId() ).thenReturn("123456");
    when( vehicle.getPoint()).thenReturn( new Point(1 , 1) );
    when(stopRepository.findByVehicleIdAndPositionNear("123456", new Point(1,1), new Distance(10.0) )  )
            .thenReturn(Arrays.asList(s2, s1, s3) );
    when(stopRepository.findByVehicleIdAndPositionNear("123456", s2.getPoint() , new Distance(10.0) )  )
            .thenReturn(Arrays.asList(s1, s3) );
    when(stopRepository.findByVehicleIdAndPositionNear("123456", s1.getPoint() , new Distance(10.0) )  )
            .thenReturn(Arrays.asList(s3) );

    List<Stop> orderedStops = routesService.orderByNearestsStops(stops, vehicle );

    verify( stopRepository , times( stops.size() ) ).findByVehicleIdAndPositionNear(anyString(), any(Point.class), any(Distance.class));
    verify( stopRepository, times(1) ).delete(s1) ;
    verify( stopRepository, times(1) ).delete(s2) ;
    verify( stopRepository, times(1) ).delete(s3) ;
    assertEquals( s2 , orderedStops.get(0) );
    assertEquals( s1 , orderedStops.get(1) );
    assertEquals( s3 , orderedStops.get(2) );
  }*/


}
