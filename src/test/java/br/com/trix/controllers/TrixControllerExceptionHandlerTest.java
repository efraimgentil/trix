package br.com.trix.controllers;

import br.com.trix.models.vo.ErrorMessage;
import br.com.trix.services.exceptions.DefaultValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
public class TrixControllerExceptionHandlerTest {

  TrixControllerExceptionHandler controller;
  MessageSource messageSource;

  @Before
  public void setUp(){
    controller = new TrixControllerExceptionHandler();
    messageSource= mock(MessageSource.class);
    controller.messageSource = messageSource;
  }

  @Test
  public void shouldSendBackADefaultMessageWhenNoMessageIsFoundForTheExceptionKey(){
    DefaultValidationException e = new DefaultValidationException("asdas" , "adasdasd");
    when(messageSource.getMessage( anyString() , any() , any() ) ).thenThrow( new NoSuchMessageException("asdas", Locale.US) );

    ErrorMessage errorMessage = controller.handleTypeMismatchException(e);

    assertEquals("Was not possible to execute the operation" , errorMessage.getError() );
  }

  @Test
  public void shouldSendTheErrorMessageWithTheValudForKeyAndDescriptionFromTheException(){
    String errorKey = "test1";
    String errorValue = "Hello";
    String descriptionKey = "test2";
    String descriptionValue = "Hello description";
    DefaultValidationException e = new DefaultValidationException( errorKey  , descriptionKey );
    when(messageSource.getMessage( eq(errorKey) , any() , any() ) ).thenReturn( errorValue );
    when(messageSource.getMessage( eq(descriptionKey) , any() , any() ) ).thenReturn( descriptionValue );

    ErrorMessage errorMessage = controller.handleTypeMismatchException(e);

    assertEquals(errorValue, errorMessage.getError() );
    assertEquals(descriptionValue, errorMessage.getDescription() );
  }


}
