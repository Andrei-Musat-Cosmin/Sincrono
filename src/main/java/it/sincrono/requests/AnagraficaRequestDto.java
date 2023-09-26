package it.sincrono.requests;

import it.sincrono.repositories.dto.AnagraficaDto;

public class AnagraficaRequestDto extends GenericRequest {
	private AnagraficaDto anagraficaDto;
	private Integer annoDataFine;
	private Integer meseDataFine;
	private Integer annoDataInizio;
	private Integer meseDataInizio;
	private Integer annoFineContratto;
	private Integer meseFineContratto;

	public AnagraficaRequestDto(AnagraficaDto anagraficaDto) {
		super();
		this.anagraficaDto = anagraficaDto;
	}

	public Integer getAnnoDataFine() {
		return annoDataFine;
	}

	public void setAnnoDataFine(Integer annoDataFine) {
		this.annoDataFine = annoDataFine;
	}

	public Integer getMeseDataFine() {
		return meseDataFine;
	}

	public void setMeseDataFine(Integer meseDataFine) {
		this.meseDataFine = meseDataFine;
	}

	public Integer getAnnoDataInizio() {
		return annoDataInizio;
	}

	public void setAnnoDataInizio(Integer annoDataInizio) {
		this.annoDataInizio = annoDataInizio;
	}

	public Integer getMeseDataInizio() {
		return meseDataInizio;
	}

	public void setMeseDataInizio(Integer meseDataInizio) {
		this.meseDataInizio = meseDataInizio;
	}

	public AnagraficaRequestDto() {
		super();
	}

	public AnagraficaDto getAnagraficaDto() {
		return anagraficaDto;
	}

	public void setAnagraficaDto(AnagraficaDto anagraficaDto) {
		this.anagraficaDto = anagraficaDto;
	}

	public Integer getAnnoFineContratto() {
		return annoFineContratto;
	}

	public void setAnnoFineContratto(Integer annoFineContratto) {
		this.annoFineContratto = annoFineContratto;
	}

	public Integer getMeseFineContratto() {
		return meseFineContratto;
	}

	public void setMeseFineContratto(Integer meseFineContratto) {
		this.meseFineContratto = meseFineContratto;
	}

	@Override
	public String toString() {
		return "AnagraficaRequestDto [anagraficaDto=" + anagraficaDto.toString() + ", annoDataFine=" + annoDataFine
				+ ", meseDataFine=" + meseDataFine + ", annoDataInizio=" + annoDataInizio + ", meseDataInizio="
				+ meseDataInizio + ", annoFineContratto=" + annoFineContratto + ", meseFineContratto="
				+ meseFineContratto + "]";
	}

}