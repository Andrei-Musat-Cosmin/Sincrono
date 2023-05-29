package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoCommesse;

public class StoricoCommesseResponse {

	private StoricoCommesse storicoCommesse;

	public StoricoCommesse getStoricoCommesse() {
		return storicoCommesse;
	}

	public void setStoricoCommesse(StoricoCommesse storicoCommesse) {
		this.storicoCommesse = storicoCommesse;
	}

	public StoricoCommesseResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoCommesseResponse(StoricoCommesse storicoCommesse) {
		super();
		this.storicoCommesse = storicoCommesse;
	}

	public void setEsito(Esito esito) {
		// TODO Auto-generated method stub

	}

}
