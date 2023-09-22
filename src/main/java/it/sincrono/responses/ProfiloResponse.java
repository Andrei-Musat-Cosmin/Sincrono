package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Profilo;

public class ProfiloResponse extends GenericResponse {

	private Profilo profilo;

	public ProfiloResponse(Esito esito, Profilo profilo) {
		super(esito);
		this.profilo = profilo;
	}

	public ProfiloResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfiloResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public Profilo getProfilo() {
		return profilo;
	}

	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}

}
