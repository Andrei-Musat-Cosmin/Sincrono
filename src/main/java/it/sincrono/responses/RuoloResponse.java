package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Ruolo;

public class RuoloResponse extends GenericResponse {

	private Ruolo ruolo;

	public RuoloResponse(Esito esito, Ruolo ruolo) {
		super(esito);
		this.ruolo = ruolo;
	}

	public RuoloResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuoloResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

}
