package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;

public class TipologicheListResponse<T> extends GenericResponse {
	private List<T> list;

	public TipologicheListResponse(Esito esito, List<T> list) {
		super(esito);
		this.list = list;
	}

	public TipologicheListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipologicheListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	
}