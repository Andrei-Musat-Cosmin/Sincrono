package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.RapportinoDto;

public class RapportinoDtoResponse extends GenericResponse {

	private RapportinoDto rapportinoDto;

	public RapportinoDtoResponse(Esito esito, RapportinoDto rapportinoDto) {
		super(esito);
		this.rapportinoDto = rapportinoDto;
	}

	public RapportinoDtoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RapportinoDto getRapportinoDto() {
		return rapportinoDto;
	}

	public void setRapportinoDto(RapportinoDto rapportinoDto) {
		this.rapportinoDto = rapportinoDto;
	}

}