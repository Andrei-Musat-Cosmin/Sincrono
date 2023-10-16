package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Anagrafica;

public class DocumentResponse extends GenericResponse {
	
	private String base64;

	public DocumentResponse(Esito esito, String base64) {
		super(esito);
		this.base64 = base64;
	}

	public DocumentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public DocumentResponse(String base64) {
	
		this.base64 = base64;
	}

	public DocumentResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	
	
	

}