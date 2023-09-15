package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.Viatura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.sql.rowset.CachedRowSet;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "viatura", path = "viatura")
public interface ViaturaRepository extends CrudRepository<Viatura, Integer> {

    @Query(value = "select  id_viatura, modelo, status from viatura;", nativeQuery = true)
    List<Object[]> listaviaturas();

    @Query(value = "select  id_viatura, modelo from viatura where status=0;", nativeQuery = true)
    List<Object[]> listaviaturasDisponiveis();

    @Query(value = "select  id_viatura, modelo from viatura where status=2;", nativeQuery = true)
    List<Object[]> listaviaturasFora();

    @Query(value = "select  id_viatura, modelo from viatura where status=1;", nativeQuery = true)
    List<Object[]> listaviaturasReservadas();
}
