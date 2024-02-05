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

	@ManyToOne
	@JoinColumn(name = "id_tipo_canale_reclutamento")
	private TipoCanaleReclutamento tipoCanaleReclutamento;

	@ManyToOne
	@JoinColumn(name = "id_stato_nascita_estera")
	private Nazione idStatoNascita;
	
	@ManyToOne
	@JoinColumn(name = "id_stato_residenza_estera")
	private Nazione idStatoResidenzaEstera;
	
	@ManyToOne
	@JoinColumn(name = "id_stato_domicilio_estero")
	private Nazione idStatoDomicilioEstero;

	@ManyToOne
	@JoinColumn(name = "id_cittadinanza_1")
	private Nazione idCittadinanza1;

	@ManyToOne
	@JoinColumn(name = "id_cittadinanza_2")
	private Nazione idCittadinanza2;

	@ManyToOne
	@JoinColumn(name = "id_comune_residenza")
	private Comune comuneResidenza;

	@ManyToOne
	@JoinColumn(name = "id_comune_di_nascita")
	private Comune comuneDiNascita;

	@ManyToOne
	@JoinColumn(name = "id_comune_domicilio")
	private Comune comuneDomicilio;

	@ManyToOne
	@JoinColumn(name = "id_provincia_residenza")
	private Provincia provinciaResidenza;

	@ManyToOne
	@JoinColumn(name = "id_provincia_di_nascita")
	private Provincia provinciaDiNascita;

	@ManyToOne
	@JoinColumn(name = "id_provincia_domicilio")
	private Provincia provinciaDomicilio;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "codice_fiscale")
	private String codiceFiscale;

	@Column(name = "data_di_nascita")
	private Date dataDiNascita;

	@Column(name = "indirizzo_residenza")
	private String indirizzoResidenza;

	@Column(name = "indirizzo_domicilio")
	private String indirizzoDomicilio;

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

	@Column(name = "categoria_protetta")
	private Boolean categoriaProtetta;

	@Column(name = "cap_residenza")
	private String capResidenza;

	@Column(name = "cap_domicilio")
	private String capDomicilio;

	@Column(name = "localita_residenza_estera")
	private String localitaResidenzaEstera;

	@Column(name = "localita_nascita_estero")
	private String localitaNascitaEstero;

	@Column(name = "localita_domicilio_estera")
	private String localitaDomicilioEstero;

	@Column(name = "piva")
	private String piva;

	@Column(name = "nome_azienda")
	private String nomeAzienda;

	@Transient
	private Boolean checkInviato;

	@Transient
	private Integer anno;

	@Transient
	private Integer mese;

	@Transient
	private Boolean residenzaDomicilioUguali;

	public Anagrafica(Integer id, Utente utente, TipoAzienda tipoAzienda, TipoCanaleReclutamento tipoCanaleReclutamento,
			Nazione idStatoNascita, Nazione idCittadinanza1, Nazione idCittadinanza2, Comune comuneResidenza,
			Comune comuneDiNascita, Comune comuneDomicilio, Provincia provinciaResidenza, Provincia provinciaDiNascita,
			Provincia provinciaDomicilio, String nome, String cognome, String codiceFiscale, Date dataDiNascita,
			String indirizzoResidenza, String indirizzoDomicilio, String cellularePrivato, String cellulareAziendale,
			String mailPrivata, String mailAziendale, String mailPec, String titoliDiStudio, String altriTitoli,
			Boolean coniugato, Boolean figliACarico, Boolean attivo, Boolean attesaLavori, Boolean categoriaProtetta,
			String capResidenza, String capDomicilio, String localitaResidenzaEstera, String localitaNascitaEstero,
			String localitaDomicilioEstero, String piva, String nomeAzienda, Boolean checkInviato, Integer anno,
			Integer mese, Boolean residenzaDomicilioUguali) {
		super();
		this.id = id;
		this.utente = utente;
		this.tipoAzienda = tipoAzienda;
		this.tipoCanaleReclutamento = tipoCanaleReclutamento;
		this.idStatoNascita = idStatoNascita;
		this.idCittadinanza1 = idCittadinanza1;
		this.idCittadinanza2 = idCittadinanza2;
		this.comuneResidenza = comuneResidenza;
		this.comuneDiNascita = comuneDiNascita;
		this.comuneDomicilio = comuneDomicilio;
		this.provinciaResidenza = provinciaResidenza;
		this.provinciaDiNascita = provinciaDiNascita;
		this.provinciaDomicilio = provinciaDomicilio;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.indirizzoResidenza = indirizzoResidenza;
		this.indirizzoDomicilio = indirizzoDomicilio;
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
		this.categoriaProtetta = categoriaProtetta;
		this.capResidenza = capResidenza;
		this.capDomicilio = capDomicilio;
		this.localitaResidenzaEstera = localitaResidenzaEstera;
		this.localitaNascitaEstero = localitaNascitaEstero;
		this.localitaDomicilioEstero = localitaDomicilioEstero;
		this.piva = piva;
		this.nomeAzienda = nomeAzienda;
		this.checkInviato = checkInviato;
		this.anno = anno;
		this.mese = mese;
		this.residenzaDomicilioUguali = residenzaDomicilioUguali;
	}

	public Anagrafica(String codiceFiscale) {
		super();
		this.codiceFiscale = codiceFiscale;
	}

	public Anagrafica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public String getCapResidenza() {
		return capResidenza;
	}

	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}

	public String getCapDomicilio() {
		return capDomicilio;
	}

	public void setCapDomicilio(String capDomicilio) {
		this.capDomicilio = capDomicilio;
	}

	public String getLocalitaResidenzaEstera() {
		return localitaResidenzaEstera;
	}

	public void setLocalitaResidenzaEstera(String localitaResidenzaEstera) {
		this.localitaResidenzaEstera = localitaResidenzaEstera;
	}

	public String getLocalitaDomicilioEstero() {
		return localitaDomicilioEstero;
	}

	public void setLocalitaDomicilioEstero(String localitaDomicilioEstero) {
		this.localitaDomicilioEstero = localitaDomicilioEstero;
	}

	public Boolean getResidenzaDomicilioUguali() {
		return residenzaDomicilioUguali;
	}

	public void setResidenzaDomicilioUguali(Boolean residenzaDomicilioUguali) {
		this.residenzaDomicilioUguali = residenzaDomicilioUguali;
	}

	public Comune getComuneDomicilio() {
		return comuneDomicilio;
	}

	public void setComuneDomicilio(Comune comuneDomicilio) {
		this.comuneDomicilio = comuneDomicilio;
	}

	public Provincia getProvinciaDomicilio() {
		return provinciaDomicilio;
	}

	public void setProvinciaDomicilio(Provincia provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}

	public Comune getComuneResidenza() {
		return comuneResidenza;
	}

	public void setComuneResidenza(Comune comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	public Provincia getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(Provincia provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
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

	public Comune getComuneDiNascita() {
		return comuneDiNascita;
	}

	public void setComuneDiNascita(Comune comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getIndirizzoDomicilio() {
		return indirizzoDomicilio;
	}

	public void setIndirizzoDomicilio(String indirizzoDomicilio) {
		this.indirizzoDomicilio = indirizzoDomicilio;
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

	public TipoCanaleReclutamento getTipoCanaleReclutamento() {
		return tipoCanaleReclutamento;
	}

	public void setTipoCanaleReclutamento(TipoCanaleReclutamento tipoCanaleReclutamento) {
		this.tipoCanaleReclutamento = tipoCanaleReclutamento;
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

	public Nazione getStatoDiNascita() {
		return idStatoNascita;
	}

	public void setStatoDiNascita(Nazione statoDiNascita) {
		this.idStatoNascita = statoDiNascita;
	}

	public Provincia getProvinciaDiNascita() {
		return provinciaDiNascita;
	}

	public void setProvinciaDiNascita(Provincia provinciaDiNascita) {
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

	public Nazione getIdStatoNascita() {
		return idStatoNascita;
	}

	public void setIdStatoNascita(Nazione idStatoNascita) {
		this.idStatoNascita = idStatoNascita;
	}

	public Nazione getIdCittadinanza1() {
		return idCittadinanza1;
	}

	public void setIdCittadinanza1(Nazione idCittadinanza1) {
		this.idCittadinanza1 = idCittadinanza1;
	}

	public Nazione getIdCittadinanza2() {
		return idCittadinanza2;
	}

	public void setIdCittadinanza2(Nazione idCittadinanza2) {
		this.idCittadinanza2 = idCittadinanza2;
	}

	public String getLocalitaNascitaEstero() {
		return localitaNascitaEstero;
	}

	public void setLocalitaNascitaEstero(String localitaNascitaEstero) {
		this.localitaNascitaEstero = localitaNascitaEstero;
	}

}
