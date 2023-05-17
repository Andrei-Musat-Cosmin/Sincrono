package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoCommesse;

public class StoricoCommesseListResponse extends GenericResponse {

	private List<StoricoCommesse> storicoCommesseList;

	public StoricoCommesseListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoricoCommesseListResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public StoricoCommesseListResponse(Esito esito, List<StoricoCommesse> storicoCommesseList) {
		super(esito);
		this.storicoCommesseList = storicoCommesseList;
	}

	public List<StoricoCommesse> getStoricoCommesseList() {
		return storicoCommesseList;
	}

	public void setStoricoCommesseList(List<StoricoCommesse> storicoCommesseList) {
		this.storicoCommesseList = storicoCommesseList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(storicoCommesseList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoricoCommesseListResponse other = (StoricoCommesseListResponse) obj;
		return Objects.equals(storicoCommesseList, other.storicoCommesseList);
	}

}
