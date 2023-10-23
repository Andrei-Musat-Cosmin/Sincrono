package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Funzione;

public class FunzioniListResponse extends GenericResponse {
	private List<Funzione> list;

	public FunzioniListResponse(Esito esito, List<Funzione> list) {
		super(esito);
		this.list = list;
	}

	public FunzioniListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FunzioniListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<Funzione> getList() {
		return list;
	}

	public void setList(List<Funzione> list) {
		this.list = list;
	}

}
