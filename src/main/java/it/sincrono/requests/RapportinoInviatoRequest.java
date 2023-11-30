package it.sincrono.requests;

import it.sincrono.entities.RapportinoInviato;

public class RapportinoInviatoRequest {

	private RapportinoInviato rapportino;

	public RapportinoInviatoRequest(RapportinoInviato rapportino) {
		super();
		this.rapportino = rapportino;
	}

	public RapportinoInviatoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RapportinoInviato getRapportino() {
		return rapportino;
	}

	public void setRapportino(RapportinoInviato rapportino) {
		this.rapportino = rapportino;
	}

}
