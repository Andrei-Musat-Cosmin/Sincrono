package it.sincrono.requests;

import java.util.Objects;

import it.sincrono.entities.Commessa;

public class CommessaRequest extends GenericRequest {

	private Commessa Commessa;

	public Commessa getCommessa() {
		return Commessa;
	}

	public void setCommessa(Commessa Commessa) {
		this.Commessa = Commessa;
	}

	public CommessaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommessaRequest(Commessa Commessa) {
		super();
		this.Commessa = Commessa;
	}

}
