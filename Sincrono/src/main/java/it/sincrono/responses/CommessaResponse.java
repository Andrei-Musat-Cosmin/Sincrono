package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Commessa;

public class CommessaResponse extends GenericResponse{
	private Commessa Commessa;

	public CommessaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommessaResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public CommessaResponse(Esito esito, it.sincrono.entities.Commessa commessa) {
		super(esito);
		Commessa = commessa;
	}

	public Commessa getCommessa() {
		return Commessa;
	}

	public void setCommessa(Commessa commessa) {
		Commessa = commessa;
	}

}
