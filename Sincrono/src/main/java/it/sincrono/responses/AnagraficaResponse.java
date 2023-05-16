package it.sincrono.responses;

import it.sincrono.entities.Anagrafica;

public class AnagraficaResponse extends GenericResponse {
	private Anagrafica anagrafica;

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
		
	}

	public AnagraficaResponse(Anagrafica anagrafica) {
		super();
		this.anagrafica = anagrafica;
	}

	public AnagraficaResponse() {
		super();
	}
	
	
}