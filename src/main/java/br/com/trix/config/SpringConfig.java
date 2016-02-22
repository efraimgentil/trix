package br.com.trix.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Configuration
@ComponentScan(basePackages =  { "br.com.trix.services" , "br.com.trix.events.services" })
@EnableMBeanExport
@EnableAspectJAutoProxy
public class SpringConfig {

  public final static String GOOGLE_KEY = "my_google_api_key";

  @Bean
  @Qualifier(SpringConfig.GOOGLE_KEY)
  public String getGoogleApiKey(){
    return "AIzaSyDS-yhi_2dNq2N75F6PIwEifqbTXmY0VsY";
  }

}
