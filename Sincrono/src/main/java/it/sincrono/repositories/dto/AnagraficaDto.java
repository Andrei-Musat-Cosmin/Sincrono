package it.sincrono.repositories.dto;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;

public class AnagraficaDto {

	private Anagrafica anagrafica;

	private Contratto contratto;

	private Commessa commessa;

	public AnagraficaDto(Anagrafica angrafica, Contratto contratto, Commessa commessa) {
		super();
		this.anagrafica = angrafica;
		this.contratto = contratto;
		this.commessa = commessa;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica angrafica) {
		this.anagrafica = angrafica;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public AnagraficaDto() {
		super();
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

}
