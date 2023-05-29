package it.sincrono.requests;

import it.sincrono.entities.Funzione;

public class FunzioneRequest {

	private Funzione funzione;

	public Funzione getFunzione() {
		return funzione;
	}

	public void setFunzione(Funzione funzione) {
		this.funzione = funzione;
	}

	public FunzioneRequest(Funzione funzione) {
		super();
		this.funzione = funzione;
	}

	public FunzioneRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
