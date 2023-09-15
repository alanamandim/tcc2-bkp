package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "reserva", path = "reserva")
public interface ReservaRepository extends CrudRepository<Reserva,Integer> {
}
