package ifrn.pi.tcc.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Vacinacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String cidade;
	@NotBlank
	private String PDose;
	@NotBlank
	private String SDose;
	@NotBlank
	private String RDose;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAtt;

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

	public String getRDose() {
		return RDose;
	}

	public void setRDose(String rDose) {
		RDose = rDose;
	}

	public LocalDate getDataAtt() {
		return dataAtt;
	}

	public void setDataAtt(LocalDate dataAtt) {
		this.dataAtt = dataAtt;
	}
}
