package it.sincrono.repositories.dto;

import java.util.List;

public class RichiestaDto {

	private Integer id;

	private Integer anno;

	private Integer mese;

	private String codiceFiscale;

	private Boolean Stato;

	private List<DuplicazioniRichiestaDto> list;

	public RichiestaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RichiestaDto(Integer id, Integer anno, Integer mese, String codiceFiscale, Boolean stato,
			List<DuplicazioniRichiestaDto> list) {
		super();
		this.id = id;
		this.anno = anno;
		this.mese = mese;
		this.codiceFiscale = codiceFiscale;
		Stato = stato;
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

	public Boolean getStato() {
		return Stato;
	}

	public void setStato(Boolean stato) {
		Stato = stato;
	}

	public List<DuplicazioniRichiestaDto> getList() {
		return list;
	}

	public void setList(List<DuplicazioniRichiestaDto> list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
