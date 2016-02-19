package br.com.trix.repositories;

import br.com.trix.models.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public interface RouteRepository  extends MongoRepository<Route, String>{
}
