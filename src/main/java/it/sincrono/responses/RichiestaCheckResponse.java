package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.RichiestaDto;

public class RichiestaCheckResponse extends GenericResponse {

	private Boolean checkElaborazione;

	public RichiestaCheckResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public RichiestaCheckResponse(Esito esito, Boolean checkElaborazione) {
		super(esito);
		this.checkElaborazione = checkElaborazione;
	}

	public Boolean getCheckElaborazione() {
		return checkElaborazione;
	}

	public void setCheckElaborazione(Boolean checkElaborazione) {
		this.checkElaborazione = checkElaborazione;
	}

}
