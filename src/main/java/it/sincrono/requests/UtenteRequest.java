package it.sincrono.requests;

import it.sincrono.entities.Utente;

public class UtenteRequest extends GenericRequest {
	private Utente utente;

	public UtenteRequest(Utente utente) {
		super();
		this.utente = utente;
	}

	public UtenteRequest() {
		super();
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}