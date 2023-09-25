package it.sincrono.responses;

import java.util.List;


import it.sincrono.beans.Esito;
import it.sincrono.entities.Operazione;

public class OperazioniListResponse extends GenericResponse {
	
	private List<Operazione> list;

	public OperazioniListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperazioniListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<Operazione> getList() {
		return list;
	}

	public void setList(List<Operazione> list) {
		this.list = list;
	}

	public OperazioniListResponse(Esito esito, List<Operazione> list) {
		super(esito);
		this.list = list;
	}
}
