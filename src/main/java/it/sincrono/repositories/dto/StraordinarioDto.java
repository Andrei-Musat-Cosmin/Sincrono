package it.sincrono.repositories.dto;

public class StraordinarioDto {



	private Double fascia1;

	private Double fascia2;

	private Double fascia3;

	public StraordinarioDto(Double fascia1, Double fascia2, Double fascia3) {
		super();
		this.fascia1 = fascia1;
		this.fascia2 = fascia2;
		this.fascia3 = fascia3;
	}

	public StraordinarioDto() {
		super();
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