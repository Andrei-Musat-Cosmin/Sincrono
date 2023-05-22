package it.sincrono.requests;

import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiRequest {
	
	private StoricoContratti storicoContratti;

	public StoricoContratti getStoricoContratti() {
		return storicoContratti;
	}

	public void setStoricoContratti(StoricoContratti storicoContratti) {
		this.storicoContratti = storicoContratti;
	}

	public StoricoContrattiRequest(StoricoContratti storicoContratti) {
		super();
		this.storicoContratti = storicoContratti;
	}

	public StoricoContrattiRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
