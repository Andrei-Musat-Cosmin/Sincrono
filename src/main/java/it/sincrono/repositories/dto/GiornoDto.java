package it.sincrono.repositories.dto;

import java.util.List;

public class GiornoDto {

	private Integer giorno;

	private List<String> cliente;

	private List<Double> oreOrdinarie;

	private Double fascia1;

	private Double fascia2;

	private Double fascia3;

	private Boolean ferie;

	private Boolean malattie;

	private Double permessi;

	private String note;

	public GiornoDto(Integer giorno, List<String> cliente, List<Double> oreOrdinarie, Double fascia1, Double fascia2,
			Double fascia3, Boolean ferie, Boolean malattie, Double permessi, String note) {
		super();
		this.giorno = giorno;
		this.cliente = cliente;
		this.oreOrdinarie = oreOrdinarie;
		this.fascia1 = fascia1;
		this.fascia2 = fascia2;
		this.fascia3 = fascia3;
		this.ferie = ferie;
		this.malattie = malattie;
		this.permessi = permessi;
		this.note = note;
	}

	public GiornoDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public Double getFascia1() {
		return fascia1;
	}

	public void setFascia1(Double fascia1) {
		this.fascia1 = fascia1;
	}

	public Double getFascia2() {
		return fascia2;
	}

	public void setFascia2(Double fascia2) {
		this.fascia2 = fascia2;
	}

	public Double getFascia3() {
		return fascia3;
	}

	public void setFascia3(Double fascia3) {
		this.fascia3 = fascia3;
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