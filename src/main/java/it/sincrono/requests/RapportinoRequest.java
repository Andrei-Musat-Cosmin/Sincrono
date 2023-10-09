package it.sincrono.requests;

import it.sincrono.entities.Commessa;

public class RapportinoRequest extends GenericRequest {

	private Integer anno;
	
	private Integer mese;
	
	private String CodiceFiscale;
	
	
	

	public RapportinoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RapportinoRequest(Integer anno, Integer mese, String codiceFiscale) {
		super();
		this.anno = anno;
		this.mese = mese;
		CodiceFiscale = codiceFiscale;
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
		return CodiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}
	
	
	

}
