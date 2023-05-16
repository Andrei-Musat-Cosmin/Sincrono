package it.sincrono.responses;

import java.util.Objects;

import it.sincrono.entities.Contratto;

public class ContrattoResponse  extends GenericResponse{

	private Contratto contratto;

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public ContrattoResponse(Contratto contratto) {
		super();
		this.contratto = contratto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(contratto);
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
		ContrattoResponse other = (ContrattoResponse) obj;
		return Objects.equals(contratto, other.contratto);
	}

	@Override
	public String toString() {
		return "ContrattoResponse [contratto=" + contratto + "]";
	}

	public ContrattoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
