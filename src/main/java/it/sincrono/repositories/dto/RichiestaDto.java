package it.sincrono.repositories.dto;

import java.util.List;
import java.util.Objects;

public class RichiestaDto {

	private Integer id;

	private Integer anno;

	private Integer mese;

	private String codiceFiscale;

	private Boolean Stato;

	private String note;

	private List<DuplicazioniRichiestaDto> list;

	public RichiestaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichiestaDto other = (RichiestaDto) obj;
		return Objects.equals(anno, other.anno) && Objects.equals(codiceFiscale, other.codiceFiscale)
				&& Objects.equals(list, other.list) && Objects.equals(mese, other.mese);

	}

	public RichiestaDto(Integer id, Integer anno, Integer mese, String codiceFiscale, Boolean stato, String note,
			List<DuplicazioniRichiestaDto> list) {
		super();
		this.id = id;
		this.anno = anno;
		this.mese = mese;
		this.codiceFiscale = codiceFiscale;
		Stato = stato;
		this.note = note;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
