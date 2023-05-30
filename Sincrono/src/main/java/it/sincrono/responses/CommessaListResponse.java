package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Commessa;

public class CommessaListResponse extends GenericResponse{
	private List<Commessa> commesseList;

	public CommessaListResponse(Esito esito, List<Commessa> commesseList) {
		super(esito);
		this.commesseList = commesseList;
	}

	public CommessaListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommessaListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public List<Commessa> getCommesseList() {
		return commesseList;
	}

	public void setCommesseList(List<Commessa> commesseList) {
		this.commesseList = commesseList;
	}

	
}
