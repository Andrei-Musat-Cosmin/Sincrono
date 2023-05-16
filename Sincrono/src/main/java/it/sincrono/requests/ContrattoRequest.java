package it.sincrono.requests;

import it.sincrono.entities.Contratto;

public class ContrattoRequest {

	private Contratto contratto;

	public ContrattoRequest(Contratto contratto) {
		super();
		this.contratto = contratto;
	}
	
	public ContrattoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}


	@Override
	public String toString() {
		return "GenericRequest [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
