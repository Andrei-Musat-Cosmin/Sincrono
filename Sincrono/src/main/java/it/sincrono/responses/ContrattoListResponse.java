package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Contratto;

public class ContrattoListResponse extends GenericResponse {

	private List<Contratto> contrattoList;

	public ContrattoListResponse(Esito esito, List<Contratto> contrattoList) {
		super(esito);
		this.contrattoList = contrattoList;
	}

	public ContrattoListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContrattoListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<Contratto> getContrattoList() {
		return contrattoList;
	}

	public void setContrattoList(List<Contratto> contrattoList) {
		this.contrattoList = contrattoList;
	}

	
}
