package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.RichiestaDto;

public class RichiestaResponse extends GenericResponse {

	private RichiestaDto richiestaDto;

	public RichiestaResponse(Esito esito, RichiestaDto richiestaDto) {
		super(esito);
		this.richiestaDto = richiestaDto;
	}

	public RichiestaResponse() {
	}

	public RichiestaDto getRichiestaDto() {
		return richiestaDto;
	}

	public void setRichiestaDto(RichiestaDto richiestaDto) {
		this.richiestaDto = richiestaDto;
	}

}
