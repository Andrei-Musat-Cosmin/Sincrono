package it.sincrono.repositories.dto;

import java.util.List;

public class GiornoDto {

	private Integer giorno;

	private List<String> cliente;

	private List<Double> oreOrdinarie;

	public GiornoDto(Integer giorno, List<String> cliente, List<Double> oreOrdinarie) {
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

	public List<String> getCliente() {
		return cliente;
	}

	public void setCliente(List<String> cliente) {
		this.cliente = cliente;
	}

	public List<Double> getOreOrdinarie() {
		return oreOrdinarie;
	}

	public void setOreOrdinarie(List<Double> oreOrdinarie) {
		this.oreOrdinarie = oreOrdinarie;
	}

}
