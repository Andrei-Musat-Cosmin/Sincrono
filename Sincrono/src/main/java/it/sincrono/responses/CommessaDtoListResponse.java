package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.CommessaDto;

public class CommessaDtoListResponse extends GenericResponse {
	private List<CommessaDto> list;

	public CommessaDtoListResponse(List<CommessaDto> list, Esito esito) {
		super(esito);
		this.list = list;
	}

	public CommessaDtoListResponse(Esito esito, List<CommessaDto> list) {
		super(esito);
		this.list = list;
	}

	public CommessaDtoListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CommessaDto> getList() {
		return list;
	}

	public void setList(List<CommessaDto> list) {
		this.list = list;
	}

}
