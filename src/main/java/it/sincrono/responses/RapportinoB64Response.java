package it.sincrono.responses;

import it.sincrono.beans.Esito;

public class RapportinoB64Response extends GenericResponse {
	private String rapportinoB64;

	public RapportinoB64Response(Esito esito, String rapportinoB64) {
		super(esito);
		this.rapportinoB64 = rapportinoB64;
	}

	public RapportinoB64Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RapportinoB64Response(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public String getRapportinoB64() {
		return rapportinoB64;
	}

	public void setRapportinoB64(String rapportinoB64) {
		this.rapportinoB64 = rapportinoB64;
	}

}