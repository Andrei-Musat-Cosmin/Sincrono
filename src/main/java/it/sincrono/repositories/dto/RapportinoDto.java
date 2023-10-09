package it.sincrono.repositories.dto;

import it.sincrono.entities.Anagrafica;

public class RapportinoDto {

	private MeseDto mese;
	private String note;
	private Anagrafica anagrafica;
	private Integer giorniUtili;
	private Double giorniLavorati;

	public RapportinoDto(MeseDto mese, String note, Anagrafica anagrafica, Integer giorniUtili, Double giorniLavorati) {
		super();
		this.mese = mese;
		this.note = note;
		this.anagrafica = anagrafica;
		this.giorniUtili = giorniUtili;
		this.giorniLavorati = giorniLavorati;
	}

	public RapportinoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeseDto getMese() {
		return mese;
	}

	public void setMese(MeseDto mese) {
		this.mese = mese;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public Integer getGiorniUtili() {
		return giorniUtili;
	}

	public void setGiorniUtili(Integer giorniUtili) {
		this.giorniUtili = giorniUtili;
	}

	public Double getGiorniLavorati() {
		return giorniLavorati;
	}

	public void setGiorniLavorati(Double giorniLavorati) {
		this.giorniLavorati = giorniLavorati;
	}

}