package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Commessa;

public class CommessaResponse {
	private Commessa Commessa;

	public Commessa getCommessa() {
		return Commessa;
	}

	public void setCommessa(Commessa Commessa) {
		this.Commessa = Commessa;
	}

	public CommessaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommessaResponse(Commessa Commessa) {
		super();
		this.Commessa = Commessa;
	}

	public void setEsito(Esito esito) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}


