package it.sincrono.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comuni")
public class Comune {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "sigla_provincia")
	private String siglaProvincia;

	@Column(name = "codice_istat")
	private String codiceIstat;

	@Column(name = "denominazione_ita_altra")
	private String denominazioneItaAltra;

	@Column(name = "denominazione_ita")
	private String denominazione_ita;

	@Column(name = "denominazione_altra")
	private String denominazione_altra;

	@Column(name = "flag_capoluogo")
	private String flag_capoluogo;

	
	public Comune() {
		super();
	}

	public Comune(Integer id, String siglaProvincia, String codiceIstat, String denominazioneItaAltra,
			String denominazione_ita, String denominazione_altra, String flag_capoluogo) {
		super();
		this.id = id;
		this.siglaProvincia = siglaProvincia;
		this.codiceIstat = codiceIstat;
		this.denominazioneItaAltra = denominazioneItaAltra;
		this.denominazione_ita = denominazione_ita;
		this.denominazione_altra = denominazione_altra;
		this.flag_capoluogo = flag_capoluogo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comune other = (Comune) obj;
		return Objects.equals(codiceIstat, other.codiceIstat)
				&& Objects.equals(denominazioneItaAltra, other.denominazioneItaAltra)
				&& Objects.equals(denominazione_altra, other.denominazione_altra)
				&& Objects.equals(denominazione_ita, other.denominazione_ita)
				&& Objects.equals(flag_capoluogo, other.flag_capoluogo) && Objects.equals(id, other.id)
				&& Objects.equals(siglaProvincia, other.siglaProvincia);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getCodiceIstat() {
		return codiceIstat;
	}

	public void setCodiceIstat(String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}

	public String getDenominazioneItaAltra() {
		return denominazioneItaAltra;
	}

	public void setDenominazioneItaAltra(String denominazioneItaAltra) {
		this.denominazioneItaAltra = denominazioneItaAltra;
	}

	public String getDenominazione_ita() {
		return denominazione_ita;
	}

	public void setDenominazione_ita(String denominazione_ita) {
		this.denominazione_ita = denominazione_ita;
	}

	public String getDenominazione_altra() {
		return denominazione_altra;
	}

	public void setDenominazione_altra(String denominazione_altra) {
		this.denominazione_altra = denominazione_altra;
	}

	public String getFlag_capoluogo() {
		return flag_capoluogo;
	}

	public void setFlag_capoluogo(String flag_capoluogo) {
		this.flag_capoluogo = flag_capoluogo;
	}

}
