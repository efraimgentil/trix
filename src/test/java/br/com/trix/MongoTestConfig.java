package br.com.trix;

import br.com.trix.config.SpringDataMongoConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = { "br.com.trix.repositories" })
public class MongoTestConfig extends SpringDataMongoConfig {

  @Bean
  @Qualifier(MONGO_DB)
  @Override
  protected String getDatabaseName() {
    return "trix";
  }

}
