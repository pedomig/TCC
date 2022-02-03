package ifrn.pi.tcc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.tcc.models.FaleConosco;
import ifrn.pi.tcc.models.Solucao;

public interface SolucaoRepository extends JpaRepository<Solucao, Long>{
	List<Solucao> findByFc(FaleConosco fc);
}
