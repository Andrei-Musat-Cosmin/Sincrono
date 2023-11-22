package it.sincrono.repositories.dto;

import java.util.List;

public class RichiestaDto {

	private Integer anno;

	private Integer mese;

	private String codiceFiscale;

	private String Stato;

	private List<DuplicazioniRichiestaDto> list;

	public RichiestaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RichiestaDto(Integer anno, Integer mese, String codiceFiscale, String stato,
			List<DuplicazioniRichiestaDto> list) {
		super();
		this.anno = anno;
		this.mese = mese;
		this.codiceFiscale = codiceFiscale;
		this.Stato = stato;
		this.list = list;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getStato() {
		return Stato;
	}

	public void setStato(String stato) {
		Stato = stato;
	}

	public List<DuplicazioniRichiestaDto> getList() {
		return list;
	}

	public void setList(List<DuplicazioniRichiestaDto> list) {
		this.list = list;
	}

}
