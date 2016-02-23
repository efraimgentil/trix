package br.com.trix.events.services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 22/02/16.
 */
@Aspect
@Component
public class EventProfiler {

  @Autowired
  EventService service;

  @Around("execution(* br.com.trix.events.services.EventService.checkEventOccurrence(..))")
  public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("Around before is running!");
    Long initialTime = System.currentTimeMillis();
    Exception exception = null;
    try {
      joinPoint.proceed(); //continue on the intercepted method
    }catch(Exception e){
      exception = e;
    }
    if(exception != null)
      throw exception;
    System.out.println("Around after is running!");
  }


}
