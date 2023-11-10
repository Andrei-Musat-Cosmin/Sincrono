package it.sincrono.repositories.dto;

import java.util.List;

public class GiornoDto {

	private List<DuplicazioniGiornoDto> duplicazioniGiornoDto;

	private Boolean ferie;

	private Boolean malattie;

	private Double permessi;

	private String note;
	
	private Integer numeroGiorno;
	
	private String nomeGiorno;
	
	private Boolean checkSmartWorking;
	
	private Boolean checkOnSite;



	public GiornoDto(List<DuplicazioniGiornoDto> duplicazioniGiornoDto, Boolean ferie, Boolean malattie,
			Double permessi, String note, Integer numeroGiorno, String nomeGiorno, Boolean checkSmartWorking,
			Boolean checkOnSite) {
		super();
		this.duplicazioniGiornoDto = duplicazioniGiornoDto;
		this.ferie = ferie;
		this.malattie = malattie;
		this.permessi = permessi;
		this.note = note;
		this.numeroGiorno = numeroGiorno;
		this.nomeGiorno = nomeGiorno;
		this.checkSmartWorking = checkSmartWorking;
		this.checkOnSite = checkOnSite;
	}

	public GiornoDto() {
		super();
	}

	public List<DuplicazioniGiornoDto> getDuplicazioniGiornoDto() {
		return duplicazioniGiornoDto;
	}

	public void setDuplicazioniGiornoDto(List<DuplicazioniGiornoDto> duplicazioniGiornoDto) {
		this.duplicazioniGiornoDto = duplicazioniGiornoDto;
	}

	public Boolean getFerie() {
		return ferie;
	}

	public void setFerie(Boolean ferie) {
		this.ferie = ferie;
	}

	public Boolean getMalattie() {
		return malattie;
	}

	public void setMalattie(Boolean malattie) {
		this.malattie = malattie;
	}

	public Double getPermessi() {
		return permessi;
	}

	public void setPermessi(Double permessi) {
		this.permessi = permessi;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getNumeroGiorno() {
		return numeroGiorno;
	}

	public void setNumeroGiorno(Integer numeroGiorno) {
		this.numeroGiorno = numeroGiorno;
	}

	public String getNomeGiorno() {
		return nomeGiorno;
	}

	public void setNomeGiorno(String nomeGiorno) {
		this.nomeGiorno = nomeGiorno;
	}

	public Boolean getCheckSmartWorking() {
		return checkSmartWorking;
	}

	public void setCheckSmartWorking(Boolean checkSmartWorking) {
		this.checkSmartWorking = checkSmartWorking;
	}

	public Boolean getCheckOnSite() {
		return checkOnSite;
	}

	public void setCheckOnSite(Boolean checkOnSite) {
		this.checkOnSite = checkOnSite;
	}
	
	

	

}