package it.sincrono.repositories.dto;

import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.Ruolo;

public class AnagraficaDto {

	private Anagrafica anagrafica;

	private Contratto contratto;

	private List<Commessa> commesse;

	private Ruolo ruolo;

	private Boolean residenzaDomicilioUguali = true;

	public AnagraficaDto(Anagrafica anagrafica, Contratto contratto, List<Commessa> commesse, Ruolo ruolo,
			Boolean residenzaDomicilioUguali) {
		super();
		this.anagrafica = anagrafica;
		this.contratto = contratto;
		this.commesse = commesse;
		this.ruolo = ruolo;
		this.residenzaDomicilioUguali = residenzaDomicilioUguali;
	}

	public AnagraficaDto() {
		super();
	}

	
	public Boolean getResidenzaDomicilioUguali() {
		return residenzaDomicilioUguali;
	}

	public void setResidenzaDomicilioUguali(Boolean residenzaDomicilioUguali) {
		this.residenzaDomicilioUguali = residenzaDomicilioUguali;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public List<Commessa> getCommesse() {
		return commesse;
	}

	public void setCommesse(List<Commessa> commesse) {
		this.commesse = commesse;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

}
