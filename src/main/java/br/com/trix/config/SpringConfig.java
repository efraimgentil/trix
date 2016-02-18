package br.com.trix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Configuration
@Import(value = { SpringDataMongoConfig.class })
public class SpringConfig {
}
