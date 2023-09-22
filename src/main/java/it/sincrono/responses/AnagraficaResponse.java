package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Anagrafica;

public class AnagraficaResponse extends GenericResponse {
	private Anagrafica anagrafica;

	public AnagraficaResponse(Esito esito, Anagrafica anagrafica) {
		super(esito);
		this.anagrafica = anagrafica;
	}

	public AnagraficaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

}