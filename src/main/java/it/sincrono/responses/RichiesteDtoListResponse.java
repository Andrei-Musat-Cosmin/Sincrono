package it.sincrono.responses;

import java.util.List;


import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.RichiestaDto;

public class RichiesteDtoListResponse extends GenericResponse {
	
	private List<RichiestaDto> list;

	public RichiesteDtoListResponse(Esito esito, List<RichiestaDto> list) {
		super(esito);
		this.list = list;
	}

	public RichiesteDtoListResponse() {
	}

	public List<RichiestaDto> getList() {
		return list;
	}

	public void setList(List<RichiestaDto> list) {
		this.list = list;
	}

}