package br.com.trix.repositories;

import br.com.trix.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
