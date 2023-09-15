package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.Inspecao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;


@RepositoryRestResource(collectionResourceRel = "inspecao", path = "inspecao")
public interface InspecaoRepository extends CrudRepository<Inspecao,Integer> {
}
