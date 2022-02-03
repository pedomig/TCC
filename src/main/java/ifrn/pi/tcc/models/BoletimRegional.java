package ifrn.pi.tcc.models;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class BoletimRegional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String cidade;
	@NotBlank
	private String casos;
	@NotBlank
	private String recuperados;
	@NotBlank
	private String observacao;
	@NotBlank
	private String obitos;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@NotNull
	private LocalTime horario;
	
	public Long getId() {
		return id;
	}
	
	public String getObitos() {
		return obitos;
	}
	public void setObitos(String obitos) {
		this.obitos = obitos;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCasos() {
		return casos;
	}
	public void setCasos(String casos) {
		this.casos = casos;
	}
	public String getRecuperados() {
		return recuperados;
	}
	public void setRecuperados(String recuperados) {
		this.recuperados = recuperados;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
}