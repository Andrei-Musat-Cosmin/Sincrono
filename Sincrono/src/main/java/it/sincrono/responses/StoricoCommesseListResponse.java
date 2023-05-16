package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoCommesse;

public class StoricoCommesseListResponse {

	private List<StoricoCommesse> storicoCommesseList;

	public List<StoricoCommesse> getStoricoCommesseList() {
		return storicoCommesseList;
	}

	public void setStoricoCommesseList(List<StoricoCommesse> storicoCommesseList) {
		this.storicoCommesseList = storicoCommesseList;
	}

	public StoricoCommesseListResponse(List<StoricoCommesse> storicoCommesseList) {
		super();
		this.storicoCommesseList = storicoCommesseList;
	}

	public StoricoCommesseListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StoricoCommesseListResponse [storicoCommesseList=" + storicoCommesseList + "]";
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

	public void setList(List<StoricoCommesse> commesse) {
		// TODO Auto-generated method stub
		
	}

	public void setEsito(Esito esito) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
