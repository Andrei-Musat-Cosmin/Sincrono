package it.sincrono.repositories.dto;

import it.sincrono.entities.Anagrafica;

public class RapportinoDto {

	private MeseDto mese;
	private Anagrafica anagrafica;

	public RapportinoDto(MeseDto mese, Anagrafica anagrafica) {
		super();
		this.mese = mese;
		this.anagrafica = anagrafica;
	}

	public RapportinoDto() {
		super();
	}

	public MeseDto getMese() {
		return mese;
	}

	public void setMese(MeseDto mese) {
		this.mese = mese;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

}
