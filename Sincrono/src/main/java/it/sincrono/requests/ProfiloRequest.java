package it.sincrono.requests;

import it.sincrono.entities.Profilo;

public class ProfiloRequest {
	
	
	private Profilo profilo;

	public Profilo getProfilo() {
		return profilo;
	}

	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}

	public ProfiloRequest(Profilo profilo) {
		super();
		this.profilo = profilo;
	}
	
	
}
