package it.sincrono.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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

	@ManyToOne
	@JoinColumn(name = "id_tipo_azienda")
	private TipoAzienda tipoAzienda;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "codice_fiscale")
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
	private String cellulareAziendale;

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
	private Boolean figliACarico;

	@Column(name = "attivo")
	private Boolean attivo;

	@Column(name = "attesa_lavori")
	private Boolean attesaLavori;

	@Column(name = "cittadinaza")
	private String cittadinanza;

	@Column(name = "stato_di_nascita")
	private String statoDiNascita;

	@Column(name = "provincia_di_nascita")
	private String provinciaDiNascita;

	@Column(name = "categoria_protetta")
	private Boolean categoriaProtetta;
	
	@Transient
    private  Boolean checkInviato; 


	public Anagrafica(Integer id, Utente utente, TipoAzienda tipoAzienda, String nome, String cognome,
			String codiceFiscale, String comuneDiNascita, Date dataDiNascita, String residenza, String domicilio,
			String cellularePrivato, String cellulareAziendale, String mailPrivata, String mailAziendale,
			String mailPec, String titoliDiStudio, String altriTitoli, Boolean coniugato, Boolean figliACarico,
			Boolean attivo, Boolean attesaLavori, String cittadinanza, String statoDiNascita, String provinciaDiNascita,
			Boolean categoriaProtetta) {
		super();
		this.id = id;
		this.utente = utente;
		this.tipoAzienda = tipoAzienda;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.comuneDiNascita = comuneDiNascita;
		this.dataDiNascita = dataDiNascita;
		this.residenza = residenza;
		this.domicilio = domicilio;
		this.cellularePrivato = cellularePrivato;
		this.cellulareAziendale = cellulareAziendale;
		this.mailPrivata = mailPrivata;
		this.mailAziendale = mailAziendale;
		this.mailPec = mailPec;
		this.titoliDiStudio = titoliDiStudio;
		this.altriTitoli = altriTitoli;
		this.coniugato = coniugato;
		this.figliACarico = figliACarico;
		this.attivo = attivo;
		this.attesaLavori = attesaLavori;
		this.cittadinanza = cittadinanza;
		this.statoDiNascita = statoDiNascita;
		this.provinciaDiNascita = provinciaDiNascita;
		this.categoriaProtetta = categoriaProtetta;
	}

	public Anagrafica(String codiceFiscale) {
		super();
		this.codiceFiscale = codiceFiscale;
	}

	public Anagrafica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anagrafica(Integer id) {
		super();
		this.id = id;
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

	public TipoAzienda getTipoAzienda() {
		return tipoAzienda;
	}

	public void setTipoAzienda(TipoAzienda tipoAzienda) {
		this.tipoAzienda = tipoAzienda;
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

	public String getCellulareAziendale() {
		return cellulareAziendale;
	}

	public void setCellulareAziendale(String cellulareAziendale) {
		this.cellulareAziendale = cellulareAziendale;
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

	public Boolean getFigliACarico() {
		return figliACarico;
	}

	public void setFigliACarico(Boolean figliACarico) {
		this.figliACarico = figliACarico;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public Boolean getAttesaLavori() {
		return attesaLavori;
	}

	public void setAttesaLavori(Boolean attesaLavori) {
		this.attesaLavori = attesaLavori;
	}

	public String getCittadinanza() {
		return cittadinanza;
	}

	public void setCittadinanza(String cittadinanza) {
		this.cittadinanza = cittadinanza;
	}

	public String getStatoDiNascita() {
		return statoDiNascita;
	}

	public void setStatoDiNascita(String statoDiNascita) {
		this.statoDiNascita = statoDiNascita;
	}

	public String getProvinciaDiNascita() {
		return provinciaDiNascita;
	}

	public void setProvinciaDiNascita(String provinciaDiNascita) {
		this.provinciaDiNascita = provinciaDiNascita;
	}

	public Boolean getCategoriaProtetta() {
		return categoriaProtetta;
	}

	public void setCategoriaProtetta(Boolean categoriaProtetta) {
		this.categoriaProtetta = categoriaProtetta;
	}

	public Boolean getCheckInviato() {
		return checkInviato;
	}

	public void setCheckInviato(Boolean checkInviato) {
		this.checkInviato = checkInviato;
	}
	
	

}
