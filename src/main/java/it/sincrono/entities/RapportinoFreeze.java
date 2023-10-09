package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rapportini_inviati")
public class RapportinoFreeze {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "codice_fiscale")
	private String codiceFiscale;

	@Column(name = "anno")
	private Integer anno;

	@Column(name = "mese")
	private Integer mese;

	@Column(name = "check_freeze")
	private Boolean checkFreeze;

	public RapportinoFreeze(Integer id, String nome, String cognome, String codiceFiscale, Integer anno, Integer mese,
			Boolean checkFreeze) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.anno = anno;
		this.mese = mese;
		this.checkFreeze = checkFreeze;
	}

	public RapportinoFreeze() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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

	public Boolean getCheckFreeze() {
		return checkFreeze;
	}

	public void setCheckFreeze(Boolean checkFreeze) {
		this.checkFreeze = checkFreeze;
	}

}