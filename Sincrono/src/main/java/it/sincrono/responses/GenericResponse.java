package it.sincrono.responses;

import java.util.Objects;

import it.sincrono.beans.Esito;

public class GenericResponse {

	private Esito esito = new Esito();

	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GenericResponse [esito=" + esito + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(esito);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericResponse other = (GenericResponse) obj;
		return Objects.equals(esito, other.esito);
	}

	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

}