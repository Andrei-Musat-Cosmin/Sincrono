package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Funzione;

public class FunzioniListResponse extends GenericResponse{
	private List<Funzione> funzioni;

	public FunzioniListResponse(Esito esito, List<Funzione> funzioni) {
		super(esito);
		this.funzioni = funzioni;
	}

	public FunzioniListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FunzioniListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<Funzione> getFunzioni() {
		return funzioni;
	}

	public void setFunzioni(List<Funzione> funzioni) {
		this.funzioni = funzioni;
	}

}
