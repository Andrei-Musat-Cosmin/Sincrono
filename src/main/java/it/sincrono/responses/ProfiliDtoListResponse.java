package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.ProfiloDto;

public class ProfiliDtoListResponse extends GenericResponse {
	private List<ProfiloDto> list;

	public ProfiliDtoListResponse(Esito esito, List<ProfiloDto> list) {
		super(esito);
		this.list = list;
	}

	public ProfiliDtoListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfiliDtoListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<ProfiloDto> getList() {
		return list;
	}

	public void setList(List<ProfiloDto> list) {
		this.list = list;
	}

}
