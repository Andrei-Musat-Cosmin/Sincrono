package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Funzione;

public class FunzioniListResponse {
	private Esito esito;
	private List<Funzione> funzioni;

	public List<Funzione> getFunzioni() {
		return funzioni;
	}

	public void setFunzioniList(List<Funzione> funzioni) {
		this.funzioni = funzioni;
	}

	public FunzioniListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FunzioniListResponse(List<Funzione> funzioni) {
		super();
		this.funzioni = funzioni;
	}

	public FunzioniListResponse(Esito esito, List<Funzione> funzioni) {
		super();
		this.esito = esito;
		this.funzioni = funzioni;
	}

	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public void setFunzioni(List<Funzione> funzioni) {
		this.funzioni = funzioni;
	}

	

	
	
	

	
	
}
