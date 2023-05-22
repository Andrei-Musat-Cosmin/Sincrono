package it.sincrono.responses;

import java.util.List;

import it.sincrono.entities.Ruolo;

public class RuoloListResponse extends GenericResponse {
	private List<Ruolo> list;

	public List<Ruolo> getList() {
		return list;
	}

	public void setList(List<Ruolo> list) {
		this.list = list;
	}

	
}
