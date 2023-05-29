package it.sincrono.repositories.dto;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.StoricoCommesse;
import it.sincrono.entities.StoricoContratti;

public class AnagraficaDto {

	private Anagrafica anagrafica;

	private Contratto contratto;

	private Commessa commessa;
	
	private StoricoContratti storicoContratti;
	
	private StoricoCommesse storicoCommessa;

	

	public AnagraficaDto(Anagrafica anagrafica, Contratto contratto, Commessa commessa,
			StoricoContratti storicoContratti, StoricoCommesse storicoCommessa) {
		super();
		this.anagrafica = anagrafica;
		this.contratto = contratto;
		this.commessa = commessa;
		this.storicoContratti = storicoContratti;
		this.storicoCommessa = storicoCommessa;
	}

	public StoricoContratti getStoricoContratti() {
		return storicoContratti;
	}

	public void setStoricoContratti(StoricoContratti storicoContratti) {
		this.storicoContratti = storicoContratti;
	}

	public StoricoCommesse getStoricoCommessa() {
		return storicoCommessa;
	}

	public void setStoricoCommessa(StoricoCommesse storicoCommessa) {
		this.storicoCommessa = storicoCommessa;
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
