package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiResponse {

	private StoricoContratti storicoContratti;

	public StoricoContratti getStoricoContratti() {
		return storicoContratti;
	}

	public void setStoricoContratti(StoricoContratti storicoContratti) {
		this.storicoContratti = storicoContratti;
	}

	public StoricoContrattiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoContrattiResponse(StoricoContratti storicoContratti) {
		super();
		this.storicoContratti = storicoContratti;
	}

	public void setEsito(Esito esito) {
		// TODO Auto-generated method stub

	}

}
