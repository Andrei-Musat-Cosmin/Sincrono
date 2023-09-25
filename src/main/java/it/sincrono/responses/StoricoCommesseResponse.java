package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoCommesse;

public class StoricoCommesseResponse extends GenericResponse{

	private StoricoCommesse storicoCommesse;

	public StoricoCommesseResponse(Esito esito, StoricoCommesse storicoCommesse) {
		super(esito);
		this.storicoCommesse = storicoCommesse;
	}

	public StoricoCommesseResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoCommesseResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public StoricoCommesse getStoricoCommesse() {
		return storicoCommesse;
	}

	public void setStoricoCommesse(StoricoCommesse storicoCommesse) {
		this.storicoCommesse = storicoCommesse;
	}

}
