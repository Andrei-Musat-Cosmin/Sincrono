package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="nazioni")
public class Nazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "sigla_nazione")
	private String siglaNazione;
	
	@Column(name = "codice_belfiore")
	private String codiceBelfiore;
	
	@Column(name = "denominazione_nazione")
	private String denominazioneNazione;
	
	@Column(name = "denominazione_cittadinanza")
	private String denominazioneCittadinanza;

	public Nazione(Integer id, String siglaNazione, String codiceBelfiore, String denominazioneNazione,
			String denominazioneCittadinanza) {
		super();
		this.id = id;
		this.siglaNazione = siglaNazione;
		this.codiceBelfiore = codiceBelfiore;
		this.denominazioneNazione = denominazioneNazione;
		this.denominazioneCittadinanza = denominazioneCittadinanza;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiglaNazione() {
		return siglaNazione;
	}

	public void setSiglaNazione(String siglaNazione) {
		this.siglaNazione = siglaNazione;
	}

	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

	public String getDenominazioneNazione() {
		return denominazioneNazione;
	}

	public void setDenominazioneNazione(String denominazioneNazione) {
		this.denominazioneNazione = denominazioneNazione;
	}

	public String getDenominazioneCittadinanza() {
		return denominazioneCittadinanza;
	}

	public void setDenominazioneCittadinanza(String denominazioneCittadinanza) {
		this.denominazioneCittadinanza = denominazioneCittadinanza;
	}
	
	
}
