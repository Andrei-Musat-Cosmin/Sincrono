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
@Table(name = "commessa")
public class Rapportino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_anagrafica")
	private Anagrafica anagrafica;

	@Column(name = "anno")
	private Integer anno;

	@Column(name = "mese")
	private Integer mese;

	@Column(name = "giorno")
	private Integer giorno;

	@Column(name = "ore")
	private Double ore;

	@Column(name = "permessi")
	private Integer permessi;

	@Column(name = "ferie")
	private Boolean ferie;

	@Column(name = "malattie")
	private Boolean malattie;

	public Rapportino(Integer id, Anagrafica anagrafica, Integer anno, Integer mese, Integer giorno, Double ore,
			Integer permessi, Boolean ferie, Boolean malattie) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
		this.ore = ore;
		this.permessi = permessi;
		this.ferie = ferie;
		this.malattie = malattie;
	}

	public Rapportino() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
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

	public Integer getPermessi() {
		return permessi;
	}

	public void setPermessi(Integer permessi) {
		this.permessi = permessi;
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

}