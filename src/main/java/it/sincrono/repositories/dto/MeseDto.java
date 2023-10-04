package it.sincrono.repositories.dto;

import java.util.List;

public class MeseDto {

	private List<GiornoDto> giorni;

	public MeseDto(List<GiornoDto> giorni) {
		super();
		this.giorni = giorni;
	}

	public MeseDto() {
	}

	public List<GiornoDto> getGiorni() {
		return giorni;
	}

	public void setGiorni(List<GiornoDto> giorni) {
		this.giorni = giorni;
	}

}
