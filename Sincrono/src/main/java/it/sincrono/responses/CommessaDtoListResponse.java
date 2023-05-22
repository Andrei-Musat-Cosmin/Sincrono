package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.CommessaDto;

public class CommessaDtoListResponse extends GenericResponse {
	private List<CommessaDto> dashboardList;
	private Esito esito;

	public CommessaDtoListResponse(List<CommessaDto> dashboardList, Esito esito) {
		super();
		this.dashboardList = dashboardList;
		this.esito = esito;
	}

	public CommessaDtoListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CommessaDto> getDashboardList() {
		return dashboardList;
	}

	public void setDashboardList(List<CommessaDto> dashboardList) {
		this.dashboardList = dashboardList;
	}

	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public void setList(List<CommessaDto> view) {
		this.dashboardList = view;

	}

}
