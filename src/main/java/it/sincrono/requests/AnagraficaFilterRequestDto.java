package it.sincrono.requests;

import it.sincrono.repositories.dto.AnagraficaDto;

public class AnagraficaFilterRequestDto extends GenericRequest {
	
	private AnagraficaDto anagraficaDto;

	private Integer anno;
	private Integer mese;
	
	public AnagraficaFilterRequestDto() {
		super();
	}

	public AnagraficaFilterRequestDto(AnagraficaDto anagraficaDto, Integer anno, Integer mese) {
		super();
		this.anagraficaDto = anagraficaDto;
		this.anno = anno;
		this.mese = mese;
	}

	public AnagraficaDto getAnagraficaDto() {
		return anagraficaDto;
	}

	public void setAnagraficaDto(AnagraficaDto anagraficaDto) {
		this.anagraficaDto = anagraficaDto;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getMese() {
		return mese;
	}

	public void setMese(Integer mese) {
		this.mese = mese;
	}
	
	
	
	
	
	
	

	

}