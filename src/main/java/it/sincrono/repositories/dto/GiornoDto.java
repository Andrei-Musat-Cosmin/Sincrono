package it.sincrono.repositories.dto;

import java.util.List;

public class GiornoDto {

	private List<DuplicazioniGiornoDto> duplicazioniGiornoDto;

	private Boolean ferie;

	private Boolean malattie;

	private Double permessi;

	private String note;

	public GiornoDto(List<DuplicazioniGiornoDto> duplicazioniGiornoDto, Boolean ferie, Boolean malattie,
			Double permessi, String note) {
		super();
		this.duplicazioniGiornoDto = duplicazioniGiornoDto;
		this.ferie = ferie;
		this.malattie = malattie;
		this.permessi = permessi;
		this.note = note;
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

}