package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Nazione;

public class NazioneListResponse extends GenericResponse {
	private List<Nazione> list;
	
	public NazioneListResponse(List<Nazione> list) {
		super();
		this.list = list;
	}

	public NazioneListResponse(Esito esito) {
		super();
		
	}

	public List<Nazione> getList() {
		return list;
	}

	public void setList(List<Nazione> list) {
		this.list = list;
	}

	public NazioneListResponse(Esito esito, List<Nazione> list) {
		super(esito);
		this.list = list;
	}

	public NazioneListResponse() {
		
	}
	
	

}
