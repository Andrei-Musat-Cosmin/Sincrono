package it.sincrono.requests;

import it.sincrono.entities.StoricoCommesse;

public class StoricoCommesseRequest {
	
	private StoricoCommesse storicoCommesse;

	public StoricoCommesse getStoricoCommesse() {
		return storicoCommesse;
	}

	public void setStoricoCommesse(StoricoCommesse storicoCommesse) {
		this.storicoCommesse = storicoCommesse;
	}

	public StoricoCommesseRequest(StoricoCommesse storicoCommesse) {
		super();
		this.storicoCommesse = storicoCommesse;
	}

	public StoricoCommesseRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
