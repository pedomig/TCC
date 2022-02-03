package ifrn.pi.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.tcc.models.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}