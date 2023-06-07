package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Utente;

public class UtenteListResponse extends GenericResponse {
	private List<Utente> list;

	public UtenteListResponse(Esito esito, List<Utente> list) {
		super(esito);
		this.list = list;
	}

	public UtenteListResponse() {
	}

	public List<Utente> getList() {
		return list;
	}

	public void setList(List<Utente> list) {
		this.list = list;
	}

}