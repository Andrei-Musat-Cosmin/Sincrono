package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Anagrafica;

public class RapportiniInviatListResponse extends GenericResponse {
	private List<RapportiniInviati> list;

	public RapportiniInviatListResponse(Esito esito, List<RapportiniInviati> list) {
		super(esito);
		this.list = list;
	}

	public RapportiniInviatListResponse() {
	}

	public List<RapportiniInviati> getList() {
		return list;
	}

	public void setList(List<RapportiniInviati> list) {
		this.list = list;
	}

}