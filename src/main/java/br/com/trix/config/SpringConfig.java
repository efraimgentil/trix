package br.com.trix.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Configuration
@ComponentScan(basePackages =  { "br.com.trix.services" , "br.com.trix.events.services" })
@EnableAspectJAutoProxy
public class SpringConfig {

  public final static String GOOGLE_KEY = "my_google_api_key";

  @Bean
  @Qualifier(SpringConfig.GOOGLE_KEY)
  public String getGoogleApiKey(){
    String google_direction_key = System.getenv("GOOGLE_DIRECTION_KEY");
    if(google_direction_key == null) {
      throw new InvalidStateException("You didn't set the google direction key as the environment variable name GOOGLE_DIRECTION_KEY");
    }
    return  google_direction_key;
  }

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasename("Messages");
    source.setDefaultEncoding("UTF-8");
    return source;
  }

}
