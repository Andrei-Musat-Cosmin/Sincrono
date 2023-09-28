package it.sincrono.repositories.dto;



public class RapportinoDto {

	private MeseDto mese;

	public RapportinoDto(MeseDto mese) {
		super();
		this.mese = mese;
	}

	public MeseDto getMese() {
		return mese;
	}

	public void setMese(MeseDto mese) {
		this.mese = mese;
	}

}
