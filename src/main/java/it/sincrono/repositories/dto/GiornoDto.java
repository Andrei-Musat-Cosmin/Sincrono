package it.sincrono.repositories.dto;

import java.util.List;

public class GiornoDto {

	private Integer giorno;

	private List<String> cliente;

	private List<Double> oreOrdinarie;

	private List<StraordinarioDto> straordinari;

	private Boolean ferie;

	private Boolean malattie;

	private Double permessi;

	private String note;

	public GiornoDto(Integer giorno, List<String> cliente, List<Double> oreOrdinarie,
			List<StraordinarioDto> straordinari, Boolean ferie, Boolean malattie, Double permessi, String note) {
		super();
		this.giorno = giorno;
		this.cliente = cliente;
		this.oreOrdinarie = oreOrdinarie;
		this.straordinari = straordinari;
		this.ferie = ferie;
		this.malattie = malattie;
		this.permessi = permessi;
		this.note = note;
	}

	public GiornoDto() {
		super();
	}

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public List<String> getCliente() {
		return cliente;
	}

	public void setCliente(List<String> cliente) {
		this.cliente = cliente;
	}

	public List<Double> getOreOrdinarie() {
		return oreOrdinarie;
	}

	public void setOreOrdinarie(List<Double> oreOrdinarie) {
		this.oreOrdinarie = oreOrdinarie;
	}

	public List<StraordinarioDto> getStraordinari() {
		return straordinari;
	}

	public void setStraordinari(List<StraordinarioDto> straordinari) {
		this.straordinari = straordinari;
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