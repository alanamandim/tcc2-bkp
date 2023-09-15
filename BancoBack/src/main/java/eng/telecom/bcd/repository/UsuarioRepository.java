package eng.telecom.bcd.repository;

import eng.telecom.bcd.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
public interface UsuarioRepository extends CrudRepository<Usuario,String> {
}
