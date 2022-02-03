package ifrn.pi.tcc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Solucao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String resposta;
	@NotBlank
	private String atuante;
	
	@OneToOne
	private FaleConosco fc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getAtuante() {
		return atuante;
	}

	public void setAtuante(String atuante) {
		this.atuante = atuante;
	}

	public FaleConosco getFc() {
		return fc;
	}

	public void setFc(FaleConosco fc) {
		this.fc = fc;
	}
}
