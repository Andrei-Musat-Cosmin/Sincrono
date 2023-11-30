package it.sincrono.requests;

import it.sincrono.entities.Anagrafica;

public class ExcelDtoRequest extends GenericRequest {
	
	private String base64;

	public ExcelDtoRequest(String base64) {
		super();
		this.base64 = base64;
	}

	public ExcelDtoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
	
	
	
}