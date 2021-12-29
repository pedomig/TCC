package projeto.pi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.pi.models.Usuario;

public interface Bd_LoginRepository extends JpaRepository<Usuario, Long> {

}
