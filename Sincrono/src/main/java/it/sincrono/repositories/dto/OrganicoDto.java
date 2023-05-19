package it.sincrono.repositories.dto;

public class OrganicoDto {

	private String azienda;
	private Integer numeroDipendenti;
	private Integer indeterminati;
	private Integer determinati;
	private Integer apprendistato;
	private Integer consulenza;
	private Integer stage;
	private Integer partitaIva;

	public OrganicoDto(String azienda, Integer numeroDipendenti, Integer indeterminati, Integer determinati,
			Integer apprendistato, Integer consulenza, Integer stage, Integer partitaIva) {
		super();
		this.azienda = azienda;
		this.numeroDipendenti = numeroDipendenti;
		this.indeterminati = indeterminati;
		this.determinati = determinati;
		this.apprendistato = apprendistato;
		this.consulenza = consulenza;
		this.stage = stage;
		this.partitaIva = partitaIva;
	}

	public OrganicoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public Integer getNumeroDipendenti() {
		return numeroDipendenti;
	}

	public void setNumeroDipendenti(Integer numeroDipendenti) {
		this.numeroDipendenti = numeroDipendenti;
	}

	public Integer getIndeterminati() {
		return indeterminati;
	}

	public void setIndeterminati(Integer indeterminati) {
		this.indeterminati = indeterminati;
	}

	public Integer getDeterminati() {
		return determinati;
	}

	public void setDeterminati(Integer determinati) {
		this.determinati = determinati;
	}

	public Integer getApprendistato() {
		return apprendistato;
	}

	public void setApprendistato(Integer apprendistato) {
		this.apprendistato = apprendistato;
	}

	public Integer getConsulenza() {
		return consulenza;
	}

	public void setConsulenza(Integer consulenza) {
		this.consulenza = consulenza;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Integer getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(Integer partitaIva) {
		this.partitaIva = partitaIva;
	}

}
