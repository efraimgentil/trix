package br.com.trix.repositories;

import br.com.trix.events.models.Occurrence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.cdi.MongoRepositoryBean;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 21/02/16.
 */
public interface OccurrenceRepository extends MongoRepository<Occurrence , String> {
}
