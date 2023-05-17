package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.beans.Esito;
import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiListResponse {

	private List<StoricoContratti> storicoContrattiList;

	public List<StoricoContratti> getStoricoContrattiList() {
		return storicoContrattiList;
	}

	public void setStoricoContrattiList(List<StoricoContratti> storicoContrattiList) {
		this.storicoContrattiList = storicoContrattiList;
	}

	public StoricoContrattiListResponse(List<StoricoContratti> storicoContrattiList) {
		super();
		this.storicoContrattiList = storicoContrattiList;
	}

	public StoricoContrattiListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StoricoContrattiListResponse [storicoContrattiList=" + storicoContrattiList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(storicoContrattiList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoricoContrattiListResponse other = (StoricoContrattiListResponse) obj;
		return Objects.equals(storicoContrattiList, other.storicoContrattiList);
	}

	public void setList(List<StoricoContratti> Contratti) {
		// TODO Auto-generated method stub
		
	}

	public void setEsito(Esito esito) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
