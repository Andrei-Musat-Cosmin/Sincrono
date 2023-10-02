package it.sincrono.requests;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.RapportinoDto;

public class RapportinoRequestDto extends GenericRequest {

	private RapportinoDto rapportinoDto;

	public RapportinoRequestDto(RapportinoDto rapportinoDto) {
		super();
		this.rapportinoDto = rapportinoDto;
	}

	public RapportinoDto getRapportinoDto() {
		return rapportinoDto;
	}

	public void setRapportinoDto(RapportinoDto rapportinoDto) {
		this.rapportinoDto = rapportinoDto;
	}

}