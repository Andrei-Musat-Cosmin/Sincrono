package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.entities.Contratto;

public class ContrattoListResponse {

	private List<Contratto> contrattoList;

	public List<Contratto> getContrattoList() {
		return contrattoList;
	}

	public ContrattoListResponse(List<Contratto> contrattoList) {
		super();
		this.contrattoList = contrattoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(contrattoList);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContrattoListResponse other = (ContrattoListResponse) obj;
		return Objects.equals(contrattoList, other.contrattoList);
	}

	@Override
	public String toString() {
		return "ContrattoListResponse [contrattoList=" + contrattoList + "]";
	}

	public ContrattoListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setList(List<Contratto> oggetti) {
		this.contrattoList = oggetti;
	}
}
