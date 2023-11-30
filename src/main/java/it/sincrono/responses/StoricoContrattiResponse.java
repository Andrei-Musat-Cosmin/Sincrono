package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiResponse extends GenericResponse {

	private StoricoContratti storicoContratti;

	public StoricoContrattiResponse(Esito esito, StoricoContratti storicoContratti) {
		super(esito);
		this.storicoContratti = storicoContratti;
	}

	public StoricoContrattiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoContrattiResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public StoricoContratti getStoricoContratti() {
		return storicoContratti;
	}

	public void setStoricoContratti(StoricoContratti storicoContratti) {
		this.storicoContratti = storicoContratti;
	}

}
