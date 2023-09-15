package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.RegistroDeMissao;
import eng.telecom.bcd.entities.Solicitacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "registroDeMissao", path = "registroDeMissao")
public interface RegistroMissaoRepository extends CrudRepository<RegistroDeMissao, Integer> {

}
