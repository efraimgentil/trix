package br.com.trix.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = { "br.com.trix.repositories" })
public class SpringDataMongoConfig extends AbstractMongoConfiguration{

  @Override
  protected String getDatabaseName() {
    return "trix";
  }

  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient();// Endereco e portas default 127.0.0.1 27017
  }

}