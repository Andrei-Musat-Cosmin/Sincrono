package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiListResponse extends GenericResponse {

	private List<StoricoContratti> storicoContrattiList;

	public StoricoContrattiListResponse(Esito esito, List<StoricoContratti> storicoContrattiList) {
		super(esito);
		this.storicoContrattiList = storicoContrattiList;
	}

	public StoricoContrattiListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoContrattiListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<StoricoContratti> getStoricoContrattiList() {
		return storicoContrattiList;
	}

	public void setStoricoContrattiList(List<StoricoContratti> storicoContrattiList) {
		this.storicoContrattiList = storicoContrattiList;
	}

}
