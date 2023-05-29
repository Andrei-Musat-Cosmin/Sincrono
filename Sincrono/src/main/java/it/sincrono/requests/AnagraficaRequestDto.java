package it.sincrono.requests;

import it.sincrono.repositories.dto.AnagraficaDto;

public class AnagraficaRequestDto extends GenericRequest {
	private AnagraficaDto anagraficaDto;

	public AnagraficaRequestDto(AnagraficaDto anagraficaDto) {
		super();
		this.anagraficaDto = anagraficaDto;
	}

	public AnagraficaRequestDto() {
		super();
	}

	public AnagraficaDto getAnagraficaDto() {
		return anagraficaDto;
	}

	public void setAnagraficaDto(AnagraficaDto anagraficaDto) {
		this.anagraficaDto = anagraficaDto;
	}

}