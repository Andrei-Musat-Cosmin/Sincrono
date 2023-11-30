package it.sincrono.repositories.dto;

public class DuplicazioniGiornoDto {

	private String cliente;

	private Double oreOrdinarie;

	private Double fascia1;

	private Double fascia2;

	private Double fascia3;

	public DuplicazioniGiornoDto() {
		super();
	}

	public DuplicazioniGiornoDto(String cliente, Double oreOrdinarie, Double fascia1, Double fascia2, Double fascia3) {
		super();

		this.cliente = cliente;
		this.oreOrdinarie = oreOrdinarie;
		this.fascia1 = fascia1;
		this.fascia2 = fascia2;
		this.fascia3 = fascia3;
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

	public Double getFascia1() {
		return fascia1;
	}

	public void setFascia1(Double fascia1) {
		this.fascia1 = fascia1;
	}

	public Double getFascia2() {
		return fascia2;
	}

	public void setFascia2(Double fascia2) {
		this.fascia2 = fascia2;
	}

	public Double getFascia3() {
		return fascia3;
	}

	public void setFascia3(Double fascia3) {
		this.fascia3 = fascia3;
	}

}