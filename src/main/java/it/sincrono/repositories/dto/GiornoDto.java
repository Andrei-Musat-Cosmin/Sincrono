package it.sincrono.repositories.dto;

public class GiornoDto {

	private Integer giorno;

	private String cliente;

	private Double oreOrdinarie;
	

	public GiornoDto(Integer giorno, String cliente, Double oreOrdinarie) {
		super();
		this.giorno = giorno;
		this.cliente = cliente;
		this.oreOrdinarie = oreOrdinarie;
	}

	public GiornoDto() {
	}

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Double getOreOrdinarie() {
		return oreOrdinarie;
	}

	public void setOreOrdinarie(Double oreOrdinarie) {
		this.oreOrdinarie = oreOrdinarie;
	}
	
	

}
