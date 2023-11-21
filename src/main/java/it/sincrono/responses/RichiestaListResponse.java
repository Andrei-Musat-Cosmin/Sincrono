package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.RichiestaDto;

public class RichiestaListResponse extends GenericResponse {

	private RichiestaDto richiestaDto;

	public RichiestaListResponse(Esito esito, RichiestaDto richiestaDto) {
		super(esito);
		this.richiestaDto = richiestaDto;
	}

	public RichiestaListResponse() {
	}

	public RichiestaDto getRichiestaDto() {
		return richiestaDto;
	}

	public void setRichiestaDto(RichiestaDto richiestaDto) {
		this.richiestaDto = richiestaDto;
	}

}
