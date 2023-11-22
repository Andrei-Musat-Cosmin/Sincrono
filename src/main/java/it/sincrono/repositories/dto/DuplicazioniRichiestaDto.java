package it.sincrono.repositories.dto;

public class DuplicazioniRichiestaDto {

	private Boolean ferie;
	private Boolean permessi;
	private String daOra;
	private String aOra;
	private Integer nGiorno;
	
	public DuplicazioniRichiestaDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DuplicazioniRichiestaDto(Boolean ferie, Boolean permessi, String daOra, String aOra, Integer nGiorno) {
		super();
		this.ferie = ferie;
		this.permessi = permessi;
		this.daOra = daOra;
		this.aOra = aOra;
		this.nGiorno = nGiorno;
	}


	public Boolean getFerie() {
		return ferie;
	}

	public void setFerie(Boolean ferie) {
		this.ferie = ferie;
	}

	public Boolean getPermessi() {
		return permessi;
	}

	public void setPermessi(Boolean permessi) {
		this.permessi = permessi;
	}



	public String getDaOra() {
		return daOra;
	}


	public void setDaOra(String daOra) {
		this.daOra = daOra;
	}


	public String getaOra() {
		return aOra;
	}


	public void setaOra(String aOra) {
		this.aOra = aOra;
	}


	public Integer getnGiorno() {
		return nGiorno;
	}

	public void setnGiorno(Integer nGiorno) {
		this.nGiorno = nGiorno;
	}

	
	

	
}
