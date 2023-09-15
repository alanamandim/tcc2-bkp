package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.MarcaModelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "marcaModelo", path = "marcaModelo")
public interface MarcaModeloRepository extends CrudRepository<MarcaModelo,String> {
}
