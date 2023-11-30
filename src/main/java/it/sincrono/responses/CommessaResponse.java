package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Commessa;

public class CommessaResponse extends GenericResponse {
	private Commessa commessa;

	public CommessaResponse(Esito esito, Commessa commessa) {
		super(esito);
		this.commessa = commessa;
	}

	public CommessaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommessaResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

}
