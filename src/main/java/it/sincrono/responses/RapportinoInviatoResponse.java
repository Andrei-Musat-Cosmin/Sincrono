package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.RapportinoInviato;

public class RapportinoInviatoResponse extends GenericResponse {

	private RapportinoInviato rapportino;

	public RapportinoInviatoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RapportinoInviatoResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public RapportinoInviatoResponse(Esito esito, RapportinoInviato rapportino) {
		super(esito);
		this.rapportino = rapportino;
	}

	public RapportinoInviato getRapportino() {
		return rapportino;
	}

	public void setRapportino(RapportinoInviato rapportino) {
		this.rapportino = rapportino;
	}

}