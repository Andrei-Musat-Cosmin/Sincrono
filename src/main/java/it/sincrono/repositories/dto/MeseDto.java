package it.sincrono.repositories.dto;

import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.Ruolo;

public class MeseDto {

	private List<GiornoDto> mese ;

	public MeseDto(List<GiornoDto> mese) {
		super();
		this.mese = mese;
	}

	public MeseDto() {
	}

	public List<GiornoDto> getMese() {
		return mese;
	}

	public void setMese(List<GiornoDto> mese) {
		this.mese = mese;
	}
	
	

}
