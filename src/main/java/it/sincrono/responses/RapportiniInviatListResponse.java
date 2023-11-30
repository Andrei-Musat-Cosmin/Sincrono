package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.RapportinoInviato;

public class RapportiniInviatListResponse extends GenericResponse {
	private List<RapportinoInviato> list;

	public RapportiniInviatListResponse(Esito esito, List<RapportinoInviato> list) {
		super(esito);
		this.list = list;
	}

	public RapportiniInviatListResponse() {
	}

	public List<RapportinoInviato> getList() {
		return list;
	}

	public void setList(List<RapportinoInviato> list) {
		this.list = list;
	}

}