package br.com.trix.services;


import br.com.trix.models.Stop;
import br.com.trix.repositories.RouteRepository;
import br.com.trix.repositories.StopRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public class RoutesServiceTest {

  RoutesService routesService;
  RouteRepository routeRepository;
  StopRepository stopRepository;

  @Before
  public void setUp(){
    routesService = new RoutesService();
    routeRepository = mock(RouteRepository.class);
    stopRepository = mock( StopRepository.class);
    routesService.routeRepository = routeRepository;
    routesService.stopRepository = stopRepository;
  }

  @Test
  public void doesSaveAllStopForTheGivenVehicleId(){
    Stop s1 = mock(Stop.class);
    Stop s2 = mock(Stop.class);
    Stop s3 = mock(Stop.class);
    List<Stop> stops = Arrays.asList(s1,s2,s3);
    Integer vehicleId = 1;

    routesService.saveAllStops(stops, vehicleId);

    InOrder inOrder = inOrder(stopRepository, s1, s2, s3);
    inOrder.verify(s1, times(1)).setVehicleId(vehicleId);
    inOrder.verify( stopRepository ).save( s1 );
    inOrder.verify(s2 , times(1) ).setVehicleId(vehicleId);
    inOrder.verify( stopRepository ).save( s2 );
    inOrder.verify(s3 , times(1) ).setVehicleId(vehicleId);
    inOrder.verify( stopRepository ).save( s3 );
  }

  @Test(expected = StopsTooFarException.class )
  public void doesThrowAnStopsTooFarExceptionIfDontFoundANearStop(){
    Stop s1 = mock(Stop.class);
    List<Stop> stops = Arrays.asList(s1);
    Integer vehicleId = 1;

    when(stopRepository.findByVehicleIdAndPointNear(anyInt(), any(Point.class), any(Distance.class)))
            .thenReturn( Collections.emptyList() );

    routesService.orderByNearestsStops(  stops , vehicleId );
  }


}
