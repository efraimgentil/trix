package br.com.trix.controllers;

import br.com.trix.models.vo.ErrorMessage;
import br.com.trix.services.exceptions.DefaultValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
@ControllerAdvice
public class TrixControllerExceptionHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(TrixControllerExceptionHandler.class);

  @Autowired
  MessageSource messageSource;

  @ExceptionHandler(DefaultValidationException.class)
  @ResponseStatus(value= HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorMessage handleTypeMismatchException(DefaultValidationException ex) {
    String error = "";
    String description = "";
    try {
      if (StringUtils.hasText(ex.getMessage()))
        error = messageSource.getMessage(ex.getMessage(), new Object[]{}, Locale.US);
      if(StringUtils.hasText( ex.getDescription() ) )
        description = messageSource.getMessage( ex.getDescription() , new Object[]{} , Locale.US );
    }catch(NoSuchMessageException nsm ){
      LOGGER.error( nsm.getMessage() , nsm );
      error="Was not possible to execute the operation";
    }
    return new ErrorMessage( error , description );
  }

}
