package it.sincrono.requests;

public class RapportinoRequest extends GenericRequest {

	private Integer anno;

	private Integer mese;

	private String codiceFiscale;

	public RapportinoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RapportinoRequest(Integer anno, Integer mese, String codiceFiscale) {
		super();
		this.anno = anno;
		this.mese = mese;
		this.codiceFiscale = codiceFiscale;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getMese() {
		return mese;
	}

	public void setMese(Integer mese) {
		this.mese = mese;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

}
