package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Anagrafica;

public class AnagraficaListResponse extends GenericResponse {
	private List<Anagrafica> list;

	public AnagraficaListResponse(Esito esito, List<Anagrafica> list) {
		super(esito);
		this.list = list;
	}

	public AnagraficaListResponse() {
	}

	public List<Anagrafica> getList() {
		return list;
	}

	public void setList(List<Anagrafica> list) {
		this.list = list;
	}

}