package br.com.trix.repositories;

import br.com.trix.models.Position;
import br.com.trix.models.Route;
import br.com.trix.models.Stop;
import br.com.trix.repositories.custom.RouteRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.*;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public interface RouteRepository  extends MongoRepository<Route, String>, RouteRepositoryCustom{

}
