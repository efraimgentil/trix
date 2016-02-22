package br.com.trix.config;

import br.com.trix.models.converters.PositionReadConverter;
import br.com.trix.models.converters.PositionWriteConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = { "br.com.trix.repositories" })
public class SpringDataMongoConfig extends AbstractMongoConfiguration{

  public static final String MONGO_DB = "mongo-database-name";

  @Bean
  @Qualifier(MONGO_DB)
  @Override
  protected String getDatabaseName() {
    return "trix";
  }

  @Bean
  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient();// Endereco e portas default 127.0.0.1 27017
  }

  public CustomConversions customConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add( new PositionWriteConverter() );
    converters.add( new PositionReadConverter() );
    return new CustomConversions(converters);
  }


}
