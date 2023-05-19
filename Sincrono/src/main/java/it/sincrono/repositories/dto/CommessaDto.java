package it.sincrono.repositories.dto;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;

public class CommessaDto {

	private Contratto contratto;

	private Commessa commessa;

	public CommessaDto(Contratto contratto, Commessa commessa) {
		super();
		this.contratto = contratto;
		this.commessa = commessa;
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

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	public CommessaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

}
