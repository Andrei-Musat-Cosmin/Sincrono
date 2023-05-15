package it.sincrono.responses;

import java.util.List;
import java.util.Objects;

import it.sincrono.entities.Commessa;

public class CommessaListResponse {
	private List<Commessa> commesseList;

	public List<Commessa> getCommesseList() {
		return commesseList;
	}

	public CommessaListResponse(List<Commessa> CommesseList) {
		super();
		this.commesseList = CommesseList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(commesseList);
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
		CommessaListResponse other = (CommessaListResponse) obj;
		return Objects.equals(commesseList, other.commesseList);
	}

	@Override
	public String toString() {
		return "CommessaListResponse [CommesseList=" + commesseList + "]";
	}

	public CommessaListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setList(List<Commessa> Commesse) {
		this.commesseList = Commesse;
	}
}
