package it.sincrono.requests;

import it.sincrono.entities.Anagrafica;

public class AnagraficaRequest extends GenericRequest {
	private Anagrafica anagrafica;

	public AnagraficaRequest(Anagrafica anagrafica) {
		super();
		this.anagrafica = anagrafica;
	}

	public AnagraficaRequest() {
		super();
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

}