package it.sincrono.repositories.dto;

import java.util.List;

public class GiornoDto {

	private List<DuplicazioniGiornoDto> duplicazioniGiornoDto;

	private Boolean ferie;

	private Boolean malattie;

	private Double permessi;

	private Double permessiRole;

	private Boolean permessiExfestivita;

	private String note;

	private Integer numeroGiorno;

	private String nomeGiorno;

	private Boolean checkSmartWorking;

	private Boolean checkOnSite;

	private Boolean festivitaNazionale;
	
	private Boolean checkFestivita;


	

	public GiornoDto(List<DuplicazioniGiornoDto> duplicazioniGiornoDto, Boolean ferie, Boolean malattie,
			Double permessi, Double permessiRole, Boolean permessiExfestivita, String note, Integer numeroGiorno,
			String nomeGiorno, Boolean checkSmartWorking, Boolean checkOnSite, Boolean festivitaNazionale,
			Boolean checkFestivita) {
		super();
		this.duplicazioniGiornoDto = duplicazioniGiornoDto;
		this.ferie = ferie;
		this.malattie = malattie;
		this.permessi = permessi;
		this.permessiRole = permessiRole;
		this.permessiExfestivita = permessiExfestivita;
		this.note = note;
		this.numeroGiorno = numeroGiorno;
		this.nomeGiorno = nomeGiorno;
		this.checkSmartWorking = checkSmartWorking;
		this.checkOnSite = checkOnSite;
		this.festivitaNazionale = festivitaNazionale;
		this.checkFestivita = checkFestivita;
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

	public Boolean getFestivitaNazionale() {
		return festivitaNazionale;
	}

	public void setFestivitaNazionale(Boolean festivitàNazionale) {
		this.festivitaNazionale = festivitàNazionale;
	}

	public Double getPermessiRole() {
		return permessiRole;
	}

	public void setPermessiRole(Double permessiRole) {
		this.permessiRole = permessiRole;
	}

	public Boolean getPermessiExfestivita() {
		return permessiExfestivita;
	}

	public void setPermessiExfestivita(Boolean permessiExfestivita) {
		this.permessiExfestivita = permessiExfestivita;
	}

	public Boolean getCheckFestivita() {
		return checkFestivita;
	}

	public void setCheckFestivita(Boolean checkFestivita) {
		this.checkFestivita = checkFestivita;
	}
	
	

}