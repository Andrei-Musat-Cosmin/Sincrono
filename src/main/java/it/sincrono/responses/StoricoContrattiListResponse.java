package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiListResponse extends GenericResponse {

	private List<StoricoContratti> list;

	public StoricoContrattiListResponse(Esito esito, List<StoricoContratti> list) {
		super(esito);
		this.list = list;
	}

	public StoricoContrattiListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoContrattiListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<StoricoContratti> getList() {
		return list;
	}

	public void setList(List<StoricoContratti> list) {
		this.list = list;
	}

}
