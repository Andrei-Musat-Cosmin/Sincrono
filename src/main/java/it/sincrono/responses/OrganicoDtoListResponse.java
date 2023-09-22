package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.OrganicoDto;

public class OrganicoDtoListResponse extends GenericResponse {
	private List<OrganicoDto> list;

	public OrganicoDtoListResponse(Esito esito, List<OrganicoDto> list) {
		super(esito);
		this.list = list;
	}

	public OrganicoDtoListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganicoDtoListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<OrganicoDto> getList() {
		return list;
	}

	public void setList(List<OrganicoDto> list) {
		this.list = list;
	}

}