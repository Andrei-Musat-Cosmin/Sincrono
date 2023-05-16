package it.sincrono.entities;

import java.util.Date;
import java.util.Objects;

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

	@Column(name = "attivo")
	private Boolean attivo;
	
	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	
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
	
	@Column(name = "coniugao")
	private Boolean coniugato;
	
	@Column(name = "figli_a_carico")
	private Boolean figliaCario;



	public Anagrafica() {
		super();
	}



	public Anagrafica(Integer id, Boolean attivo, Utente utente, String aziendaTipo, String cognome, String nome,
			String codiceFiscale, String comuneDiNascita, Date dataDiNascita, String residenza, String domicilio,
			String cellularePrivato, String celllulareAziendale, String mailPrivata, String mailAziendale,
			String mailPec, String titoliDiStudio, String altriTitoli, Boolean coniugato, Boolean figliaCario) {
		super();
		this.id = id;
		this.attivo = attivo;
		this.utente = utente;
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
		this.figliaCario = figliaCario;
	}

	
	
	


	@Override
	public int hashCode() {
		return Objects.hash(altriTitoli, attivo, aziendaTipo, celllulareAziendale, cellularePrivato, codiceFiscale,
				cognome, comuneDiNascita, coniugato, dataDiNascita, domicilio, figliaCario, id, mailAziendale, mailPec,
				mailPrivata, nome, residenza, titoliDiStudio, utente);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anagrafica other = (Anagrafica) obj;
		return Objects.equals(altriTitoli, other.altriTitoli) && Objects.equals(attivo, other.attivo)
				&& Objects.equals(aziendaTipo, other.aziendaTipo)
				&& Objects.equals(celllulareAziendale, other.celllulareAziendale)
				&& Objects.equals(cellularePrivato, other.cellularePrivato)
				&& Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(comuneDiNascita, other.comuneDiNascita) && Objects.equals(coniugato, other.coniugato)
				&& Objects.equals(dataDiNascita, other.dataDiNascita) && Objects.equals(domicilio, other.domicilio)
				&& Objects.equals(figliaCario, other.figliaCario) && Objects.equals(id, other.id)
				&& Objects.equals(mailAziendale, other.mailAziendale) && Objects.equals(mailPec, other.mailPec)
				&& Objects.equals(mailPrivata, other.mailPrivata) && Objects.equals(nome, other.nome)
				&& Objects.equals(residenza, other.residenza) && Objects.equals(titoliDiStudio, other.titoliDiStudio)
				&& Objects.equals(utente, other.utente);
	}
	
	


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Boolean getAttivo() {
		return attivo;
	}



	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
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



	public void setAzienda_tipo(String aziendaTipo) {
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
		return figliaCario;
	}



	public void setFigliaCario(Boolean figliaCario) {
		this.figliaCario = figliaCario;
	}

	
	



	
}
