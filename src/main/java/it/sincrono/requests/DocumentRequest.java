package it.sincrono.requests;

public class DocumentRequest extends GenericRequest {

	private String codiceFiscale;

	private String base64;

	public DocumentRequest() {
		super();
	}

	public DocumentRequest(String codiceFiscale, String base64) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.base64 = base64;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

}