package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.Solicitacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "solicitacao", path = "solicitacao")
public interface SolicitacaoRepository extends CrudRepository<Solicitacao, Integer> {
    @Query(value = "select * from solicitacao where status_solicitacao=1;", nativeQuery = true)
    List<Object[]>listaSolicitacaoAprovada();

    @Query(value = "select * from solicitacao where status_solicitacao=0;", nativeQuery = true)
    List<Object[]>listaSolicitacaoAguardandoAprovacao();

}
