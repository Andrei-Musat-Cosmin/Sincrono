package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rapportini")
public class Rapportino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "anno")
	private Integer anno;

	@Column(name = "mese")
	private Integer mese;

	@Column(name = "giorno")
	private Integer giorno;

	@Column(name = "ore")
	private Double ore;

	@Column(name = "ferie")
	private Boolean ferie;

	@Column(name = "malattie")
	private Boolean malattie;

	@Column(name = "permessi")
	private Integer permessi;
	
	@ManyToOne
	@JoinColumn(name = "id_anagrafica")
	private Anagrafica anagrafica;
	

	public Rapportino() {
		super();
	}

	public Rapportino(Integer id, Integer anno, Integer mese, Integer giorno, Double ore, Boolean ferie,
			Boolean malattie, Integer permessi) {
		super();
		this.id = id;
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
		this.ore = ore;
		this.ferie = ferie;
		this.malattie = malattie;
		this.permessi = permessi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public Double getOre() {
		return ore;
	}

	public void setOre(Double ore) {
		this.ore = ore;
	}

	public Boolean getFerie() {
		return ferie;
	}

	public void setFerie(Boolean ferie) {
		this.ferie = ferie;
	}

	public Boolean getMalattie() {
		return malattie;
	}

	public void setMalattie(Boolean malattie) {
		this.malattie = malattie;
	}

	public Integer getPermessi() {
		return permessi;
	}

	public void setPermessi(Integer permessi) {
		this.permessi = permessi;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}
	
	

}
