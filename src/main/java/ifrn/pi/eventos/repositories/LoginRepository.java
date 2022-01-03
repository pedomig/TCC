package ifrn.pi.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.models.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}