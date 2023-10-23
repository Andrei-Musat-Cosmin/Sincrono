package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoCommesse;

public class StoricoCommesseListResponse extends GenericResponse {

	private List<StoricoCommesse> list;

	public StoricoCommesseListResponse(Esito esito, List<StoricoCommesse> list) {
		super(esito);
		this.list = list;
	}

	public StoricoCommesseListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoCommesseListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<StoricoCommesse> getList() {
		return list;
	}

	public void setList(List<StoricoCommesse> list) {
		this.list = list;
	}

}
