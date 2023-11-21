package it.sincrono.repositories.dto;

public class DuplicazioniRichiestaDto {

	private Boolean ferie;
	private Boolean permessi;
	private Integer daGiorno;
	private Integer aGiorno;

	public DuplicazioniRichiestaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DuplicazioniRichiestaDto(Boolean ferie, Boolean permessi, Integer daGiorno, Integer aGiorno) {
		super();
		this.ferie = ferie;
		this.permessi = permessi;
		this.daGiorno = daGiorno;
		this.aGiorno = aGiorno;
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

	public Integer getDaGiorno() {
		return daGiorno;
	}

	public void setDaGiorno(Integer daGiorno) {
		this.daGiorno = daGiorno;
	}

	public Integer getaGiorno() {
		return aGiorno;
	}

	public void setaGiorno(Integer aGiorno) {
		this.aGiorno = aGiorno;
	}

}
