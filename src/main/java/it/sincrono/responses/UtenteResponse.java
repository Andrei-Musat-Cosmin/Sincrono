package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Utente;

public class UtenteResponse extends GenericResponse {
	private Utente utente;

	public UtenteResponse(Esito esito, Utente utente) {
		super(esito);
		this.utente = utente;
	}

	public UtenteResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}