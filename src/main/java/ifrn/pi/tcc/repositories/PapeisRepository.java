package ifrn.pi.tcc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.tcc.models.Papel;

public interface PapeisRepository extends JpaRepository<Papel, Long> {
	List<Papel> findByTipo(char tipo);
}
