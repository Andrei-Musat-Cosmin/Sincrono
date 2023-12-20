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

	@ManyToOne
	@JoinColumn(name = "id_anagrafica")
	private Anagrafica anagrafica;

	@Column(name = "anno")
	private Integer anno;

	@Column(name = "mese")
	private Integer mese;

	@Column(name = "giorno")
	private Integer giorno;

	@Column(name = "1_fascia")
	private Double fascia1;

	@Column(name = "2_fascia")
	private Double fascia2;

	@Column(name = "3_fascia")
	private Double fascia3;

	@Column(name = "ore")
	private Double ore;

	@Column(name = "permessi")
	private Double permessi;

	@Column(name = "permessiRole")
	private Double permessiRole;
	
	@Column(name = "permessiExfestivita")
	private Boolean permessiExfestivita;
	
	@Column(name = "ferie")
	private Boolean ferie;

	@Column(name = "malattie")
	private Boolean malattie;

	public Rapportino(Integer id, Anagrafica anagrafica, Integer anno, Integer mese, Integer giorno, Double fascia1,
			Double fascia2, Double fascia3, Double ore, Double permessi, Boolean ferie, Boolean malattie,Double permessiRole,Boolean permessiExfestivita) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
		this.fascia1 = fascia1;
		this.fascia2 = fascia2;
		this.fascia3 = fascia3;
		this.ore = ore;
		this.permessi = permessi;
		this.ferie = ferie;
		this.malattie = malattie;
		this.permessiRole = permessiRole;
		this.permessiExfestivita = permessiExfestivita;
	}

	public Rapportino() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double getPermessiRole() {
		return permessiRole;
	}

	public void setPermessiRole(Double permessiRole) {
		this.permessiRole = permessiRole;
	}

	public Boolean getPermessiExfestivita() {
		return permessiExfestivita;
	}

	public void setPermessiExfestivita(Boolean permessiExfestivita) {
		this.permessiExfestivita = permessiExfestivita;
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

	public Double getOre() {
		return ore;
	}

	public void setOre(Double ore) {
		this.ore = ore;
	}

	public Double getPermessi() {
		return permessi;
	}

	public void setPermessi(Double permessi) {
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