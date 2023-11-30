package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Ruolo;

public class RuoloListResponse extends GenericResponse {
	private List<Ruolo> list;

	public RuoloListResponse(Esito esito, List<Ruolo> list) {
		super(esito);
		this.list = list;
	}

	public RuoloListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuoloListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<Ruolo> getList() {
		return list;
	}

	public void setList(List<Ruolo> list) {
		this.list = list;
	}

}
