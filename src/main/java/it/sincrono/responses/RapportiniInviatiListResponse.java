package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.RapportinoInviato;

public class RapportiniInviatiListResponse extends GenericResponse {
	private List<RapportinoInviato> list;

	public RapportiniInviatiListResponse(Esito esito, List<RapportinoInviato> list) {
		super(esito);
		this.list = list;
	}

	public RapportiniInviatiListResponse() {
	}

	public List<RapportinoInviato> getList() {
		return list;
	}

	public void setList(List<RapportinoInviato> list) {
		this.list = list;
	}

}