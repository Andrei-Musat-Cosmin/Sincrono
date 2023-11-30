package it.sincrono.requests;

import it.sincrono.repositories.dto.RichiestaDto;

public class RichiestaRequest extends GenericRequest {

	private RichiestaDto richiestaDto;

	public RichiestaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RichiestaRequest(RichiestaDto richiestaDto) {
		super();
		this.richiestaDto = richiestaDto;
	}

	public RichiestaDto getRichiestaDto() {
		return richiestaDto;
	}

	public void setRichiestaDto(RichiestaDto richiestaDto) {
		this.richiestaDto = richiestaDto;
	}

}
