package ifrn.pi.eventos.models;

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
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String cidade;
	@NotBlank
	private String Cep;
	@NotBlank
	private String PDose;
	@NotBlank
	private String SDose;
	
	@NotBlank
	private String casos;
	@NotBlank
	private String recuperados;
	@NotBlank
	private String observacao;
	
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@NotNull
	private LocalTime horario;
	public Long getId() {
		return id;
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
	public String getCep() {
		return Cep;
	}
	public void setCep(String cep) {
		Cep = cep;
	}
	public String getPDose() {
		return PDose;
	}
	public void setPDose(String pDose) {
		PDose = pDose;
	}
	public String getSDose() {
		return SDose;
	}
	public void setSDose(String sDose) {
		SDose = sDose;
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
	@Override
	public String toString() {
		return "Evento [id=" + id + ", cidade=" + cidade + ", Cep=" + Cep + ", PDose=" + PDose + ", SDose=" + SDose
				+ ", casos=" + casos + ", recuperados=" + recuperados + ", observacao=" + observacao + ", data=" + data
				+ ", horario=" + horario + "]";
	}
	
	
	

	
	


}
