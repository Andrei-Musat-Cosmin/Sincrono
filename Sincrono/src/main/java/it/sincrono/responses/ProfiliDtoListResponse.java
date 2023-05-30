package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.ProfiloDto;

public class ProfiliDtoListResponse extends GenericResponse {
	private List<ProfiloDto> profili;

	public ProfiliDtoListResponse(Esito esito, List<ProfiloDto> profili) {
		super(esito);
		this.profili = profili;
	}

	public ProfiliDtoListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfiliDtoListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<ProfiloDto> getProfili() {
		return profili;
	}

	public void setProfili(List<ProfiloDto> profili) {
		this.profili = profili;
	}


}
