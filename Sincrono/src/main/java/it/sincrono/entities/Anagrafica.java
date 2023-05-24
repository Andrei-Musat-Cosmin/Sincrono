package it.sincrono.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "anagrafica")
public class Anagrafica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@Column(name = "attivo")
	private Boolean attivo;

	@Column(name = "azienda_tipo")
	private String aziendaTipo;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cf")
	private String codiceFiscale;

	@Column(name = "comune_di_nascita")
	private String comuneDiNascita;

	@Column(name = "data_di_nascita")
	private Date dataDiNascita;

	@Column(name = "residenza")
	private String residenza;

	@Column(name = "domicilio")
	private String domicilio;

	@Column(name = "cellulare_privato")
	private String cellularePrivato;

	@Column(name = "cellulare_aziendale")
	private String celllulareAziendale;

	@Column(name = "mail_privata")
	private String mailPrivata;

	@Column(name = "mail_Aziendale")
	private String mailAziendale;

	@Column(name = "mail_pec")
	private String mailPec;

	@Column(name = "titoli_di_studio")
	private String titoliDiStudio;

	@Column(name = "altri_titoli")
	private String altriTitoli;

	@Column(name = "coniugato")
	private Boolean coniugato;

	@Column(name = "figli_a_carico")
	private Boolean figliaCarico;

	public Anagrafica(Integer id, Utente utente, Boolean attivo, String aziendaTipo, String cognome, String nome,
			String codiceFiscale, String comuneDiNascita, Date dataDiNascita, String residenza, String domicilio,
			String cellularePrivato, String celllulareAziendale, String mailPrivata, String mailAziendale,
			String mailPec, String titoliDiStudio, String altriTitoli, Boolean coniugato, Boolean figliaCarico) {
		super();
		this.id = id;
		this.utente = utente;
		this.attivo = attivo;
		this.aziendaTipo = aziendaTipo;
		this.cognome = cognome;
		this.nome = nome;
		this.codiceFiscale = codiceFiscale;
		this.comuneDiNascita = comuneDiNascita;
		this.dataDiNascita = dataDiNascita;
		this.residenza = residenza;
		this.domicilio = domicilio;
		this.cellularePrivato = cellularePrivato;
		this.celllulareAziendale = celllulareAziendale;
		this.mailPrivata = mailPrivata;
		this.mailAziendale = mailAziendale;
		this.mailPec = mailPec;
		this.titoliDiStudio = titoliDiStudio;
		this.altriTitoli = altriTitoli;
		this.coniugato = coniugato;
		this.figliaCarico = figliaCarico;
	}

	public Anagrafica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getAziendaTipo() {
		return aziendaTipo;
	}

	public void setAziendaTipo(String aziendaTipo) {
		this.aziendaTipo = aziendaTipo;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getComuneDiNascita() {
		return comuneDiNascita;
	}

	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCellularePrivato() {
		return cellularePrivato;
	}

	public void setCellularePrivato(String cellularePrivato) {
		this.cellularePrivato = cellularePrivato;
	}

	public String getCelllulareAziendale() {
		return celllulareAziendale;
	}

	public void setCelllulareAziendale(String celllulareAziendale) {
		this.celllulareAziendale = celllulareAziendale;
	}

	public String getMailPrivata() {
		return mailPrivata;
	}

	public void setMailPrivata(String mailPrivata) {
		this.mailPrivata = mailPrivata;
	}

	public String getMailAziendale() {
		return mailAziendale;
	}

	public void setMailAziendale(String mailAziendale) {
		this.mailAziendale = mailAziendale;
	}

	public String getMailPec() {
		return mailPec;
	}

	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}

	public String getTitoliDiStudio() {
		return titoliDiStudio;
	}

	public void setTitoliDiStudio(String titoliDiStudio) {
		this.titoliDiStudio = titoliDiStudio;
	}

	public String getAltriTitoli() {
		return altriTitoli;
	}

	public void setAltriTitoli(String altriTitoli) {
		this.altriTitoli = altriTitoli;
	}

	public Boolean getConiugato() {
		return coniugato;
	}

	public void setConiugato(Boolean coniugato) {
		this.coniugato = coniugato;
	}

	public Boolean getFigliaCario() {
		return figliaCarico;
	}

	public void setFigliaCario(Boolean figliaCario) {
		this.figliaCarico = figliaCario;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

}
