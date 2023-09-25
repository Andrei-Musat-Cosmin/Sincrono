package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Contratto;

public class ContrattoResponse extends GenericResponse {

	private Contratto contratto;

	public ContrattoResponse(Esito esito, Contratto contratto) {
		super(esito);
		this.contratto = contratto;
	}

	public ContrattoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContrattoResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

}
