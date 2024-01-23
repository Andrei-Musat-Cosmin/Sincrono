package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "province")
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "sigla_provincia")
	private String siglaProvincia;

	@Column(name = "denominazione_provincia")
	private String denominazione_provincia;

	@Column(name = "tipologia_provincia")
	private String tipologiaProvincia;

	@Column(name = "numeroComuni")
	private Integer numeroComuni;

	public Provincia() {
		super();
	}

	public Provincia(Integer id, String siglaProvincia, String denominazione_provincia, String tipologiaProvincia,
			Integer numeroComuni) {
		super();
		this.id = id;
		this.siglaProvincia = siglaProvincia;
		this.denominazione_provincia = denominazione_provincia;
		this.tipologiaProvincia = tipologiaProvincia;
		this.numeroComuni = numeroComuni;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getDenominazione_provincia() {
		return denominazione_provincia;
	}

	public void setDenominazione_provincia(String denominazione_provincia) {
		this.denominazione_provincia = denominazione_provincia;
	}

	public String getTipologiaProvincia() {
		return tipologiaProvincia;
	}

	public void setTipologiaProvincia(String tipologiaProvincia) {
		this.tipologiaProvincia = tipologiaProvincia;
	}

	public Integer getNumeroComuni() {
		return numeroComuni;
	}

	public void setNumeroComuni(Integer numeroComuni) {
		this.numeroComuni = numeroComuni;
	}

}
