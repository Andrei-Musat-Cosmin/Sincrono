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
@Table(name = "richieste")
public class Richieste {

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

	@Column(name = "stato")
	private Boolean stato;

	public Richieste() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Richieste(Integer id, Anagrafica anagrafica, Integer anno, Integer mese, Boolean stato) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
		this.anno = anno;
		this.mese = mese;
		this.stato = stato;
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

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}
	
	
	



	
	
	
}
