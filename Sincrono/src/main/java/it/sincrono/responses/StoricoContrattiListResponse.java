package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiListResponse extends GenericResponse {

	private List<StoricoContratti> storicoContrattiList;

	public StoricoContrattiListResponse() {
		super();
	}

	public StoricoContrattiListResponse(List<StoricoContratti> storicoContrattiList) {
		super();
		this.storicoContrattiList = storicoContrattiList;
	}

	public List<StoricoContratti> getStoricoContrattiList() {
		return storicoContrattiList;
	}

	public void setStoricoContrattiList(List<StoricoContratti> storicoContrattiList) {
		this.storicoContrattiList = storicoContrattiList;
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

}
