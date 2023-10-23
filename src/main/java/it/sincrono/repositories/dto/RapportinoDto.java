package it.sincrono.repositories.dto;

import it.sincrono.entities.Anagrafica;

public class RapportinoDto {

	private MeseDto mese;
	private String note;
	private Anagrafica anagrafica;
	private Integer giorniUtili;
	private Double giorniLavorati;
	private Integer annoRequest;
	private Integer meseRequest;

	public RapportinoDto() {
		super();
	}

	public RapportinoDto(MeseDto mese, String note, Anagrafica anagrafica, Integer giorniUtili, Double giorniLavorati,
			Integer annoRequest, Integer meseRequest) {
		super();
		this.mese = mese;
		this.note = note;
		this.anagrafica = anagrafica;
		this.giorniUtili = giorniUtili;
		this.giorniLavorati = giorniLavorati;
		this.annoRequest = annoRequest;
		this.meseRequest = meseRequest;
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

	public Integer getAnnoRequest() {
		return annoRequest;
	}

	public void setAnnoRequest(Integer annoRequest) {
		this.annoRequest = annoRequest;
	}

	public Integer getMeseRequest() {
		return meseRequest;
	}

	public void setMeseRequest(Integer meseRequest) {
		this.meseRequest = meseRequest;
	}

}