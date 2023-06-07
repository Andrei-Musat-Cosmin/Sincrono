package it.sincrono.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contratto")
public class Contratto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_tipo_contratto")
	private TipoContratto tipoContratto;

	@ManyToOne
	@JoinColumn(name = "id_tipo_livello")
	private LivelloContratto livelloContratto;

	@ManyToOne
	@JoinColumn(name = "id_tipo_azienda")
	private TipoAzienda tipoAzienda;

	@ManyToOne
	@JoinColumn(name = "id_contratto_nazionale")
	private ContrattoNazionale contrattoNazionale;

	@Column(name = "attivo")
	private Boolean attivo;

	@Column(name = "sede_assunzione")
	private String sedeAssunzione;

	@Column(name = "qualifica")
	private String qualifica;

	@Column(name = "data_assunzione")
	private Date dataAssunzione;

	@Column(name = "data_inizio_prova")
	private Date dataInizioProva;

	@Column(name = "data_fine_prova")
	private Date dataFineProva;

	@Column(name = "data_fine_rapporto")
	private Date dataFineRapporto;

	@Column(name = "mesi_durata")
	private Integer mesiDurata;

	@Column(name = "livello_iniziale")
	private String livelloIniziale;

	@Column(name = "livello_attuale")
	private String livelloAttuale;

	@Column(name = "livello_finale")
	private String livelloFinale;

	@Column(name = "dimissioni")
	private Boolean dimissioni;

	@Column(name = "part_time")
	private Boolean partTime;

	@Column(name = "part_timeA")
	private Boolean partTimeA;

	@Column(name = "retribuzione_mensile_lorda")
	private String retribuzioneMensileLorda;

	@Column(name = "superminimo_mensile")
	private String superminimoMensile;

	@Column(name = "ral_annua")
	private String ralAnnua;

	@Column(name = "superminimo_ral")
	private String superminimoRal;

	@Column(name = "diaria_mese")
	private String diariaMese;

	@Column(name = "diaria_gg")
	private String diariaGg;

//	@Column(name = "data_visita_medica")
//	private Date dataVisitaMedica;
	
	@Column(name = "ticket")
	private String ticket;

	@Column(name = "valore_ticket")
	private String valoreTicket;

	@Column(name = "categoria_protetta")
	private Boolean categoriaProtetta;

	@Column(name = "tutor")
	private String tutor;

	@Column(name = "pfi")
	private String pfi;

	@Column(name = "assicurazione_obbligatoria")
	private String assicurazioneObbligatoria;

	@Column(name = "corso_sicurezza")
	private Date corsoSicurezza;

	@Column(name = "motivazione_fine_rapporto")
	private String motivazioneFineRapporto;

	@Column(name = "pc")
	private Boolean pc;

	@Column(name = "scatti_anzianita")
	private String scattiAnzianita;

	@Column(name = "tariffa_partita_iva")
	private String tariffaPartitaIva;

	@Column(name = "canale_reclutamento")
	private String canaleReclutamento;

	public Contratto(Integer id, TipoContratto tipoContratto, LivelloContratto livelloContratto,
			TipoAzienda tipoAzienda, ContrattoNazionale contrattoNazionale, Boolean attivo, String sedeAssunzione,
			String qualifica, Date dataAssunzione, Date dataInizioProva, Date dataFineProva, Date dataFineRapporto,
			Integer mesiDurata, String livelloIniziale, String livelloAttuale, String livelloFinale, Boolean dimissioni,
			Boolean partTime, Boolean partTimeA, String retribuzioneMensileLorda, String superminimoMensile,
			String ralAnnua, String superminimoRal, String diariaMese, String diariaGg, String ticket,
			String valoreTicket, Boolean categoriaProtetta, String tutor, String pfi, String assicurazioneObbligatoria,
			Date corsoSicurezza, String motivazioneFineRapporto, Boolean pc, String scattiAnzianita,
			String tariffaPartitaIva, String canaleReclutamento) {
		super();
		this.id = id;
		this.tipoContratto = tipoContratto;
		this.livelloContratto = livelloContratto;
		this.tipoAzienda = tipoAzienda;
		this.contrattoNazionale = contrattoNazionale;
		this.attivo = attivo;
		this.sedeAssunzione = sedeAssunzione;
		this.qualifica = qualifica;
		this.dataAssunzione = dataAssunzione;
		this.dataInizioProva = dataInizioProva;
		this.dataFineProva = dataFineProva;
		this.dataFineRapporto = dataFineRapporto;
		this.mesiDurata = mesiDurata;
		this.livelloIniziale = livelloIniziale;
		this.livelloAttuale = livelloAttuale;
		this.livelloFinale = livelloFinale;
		this.dimissioni = dimissioni;
		this.partTime = partTime;
		this.partTimeA = partTimeA;
		this.retribuzioneMensileLorda = retribuzioneMensileLorda;
		this.superminimoMensile = superminimoMensile;
		this.ralAnnua = ralAnnua;
		this.superminimoRal = superminimoRal;
		this.diariaMese = diariaMese;
		this.diariaGg = diariaGg;
		this.ticket = ticket;
		this.valoreTicket = valoreTicket;
		this.categoriaProtetta = categoriaProtetta;
		this.tutor = tutor;
		this.pfi = pfi;
		this.assicurazioneObbligatoria = assicurazioneObbligatoria;
		this.corsoSicurezza = corsoSicurezza;
		this.motivazioneFineRapporto = motivazioneFineRapporto;
		this.pc = pc;
		this.scattiAnzianita = scattiAnzianita;
		this.tariffaPartitaIva = tariffaPartitaIva;
		this.canaleReclutamento = canaleReclutamento;

	}

	public Contratto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Contratto(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoContratto getTipoContratto() {
		return tipoContratto;
	}

	public void setTipoContratto(TipoContratto tipoContratto) {
		this.tipoContratto = tipoContratto;
	}

	public LivelloContratto getLivelloContratto() {
		return livelloContratto;
	}

	public void setLivelloContratto(LivelloContratto livelloContratto) {
		this.livelloContratto = livelloContratto;
	}

	public TipoAzienda getTipoAzienda() {
		return tipoAzienda;
	}

	public void setTipoAzienda(TipoAzienda tipoAzienda) {
		this.tipoAzienda = tipoAzienda;
	}

	public ContrattoNazionale getContrattoNazionale() {
		return contrattoNazionale;
	}

	public void setContrattoNazionale(ContrattoNazionale contrattoNazionale) {
		this.contrattoNazionale = contrattoNazionale;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public String getSedeAssunzione() {
		return sedeAssunzione;
	}

	public void setSedeAssunzione(String sedeAssunzione) {
		this.sedeAssunzione = sedeAssunzione;
	}

	public String getQualifica() {
		return qualifica;
	}

	public void setQualifica(String qualifica) {
		this.qualifica = qualifica;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Date getDataInizioProva() {
		return dataInizioProva;
	}

	public void setDataInizioProva(Date dataInizioProva) {
		this.dataInizioProva = dataInizioProva;
	}

	public Date getDataFineProva() {
		return dataFineProva;
	}

	public void setDataFineProva(Date dataFineProva) {
		this.dataFineProva = dataFineProva;
	}

	public Date getDataFineRapporto() {
		return dataFineRapporto;
	}

	public void setDataFineRapporto(Date dataFineRapporto) {
		this.dataFineRapporto = dataFineRapporto;
	}

	public Integer getMesiDurata() {
		return mesiDurata;
	}

	public void setMesiDurata(Integer mesiDurata) {
		this.mesiDurata = mesiDurata;
	}

	public String getLivelloIniziale() {
		return livelloIniziale;
	}

	public void setLivelloIniziale(String livelloIniziale) {
		this.livelloIniziale = livelloIniziale;
	}

	public String getLivelloAttuale() {
		return livelloAttuale;
	}

	public void setLivelloAttuale(String livelloAttuale) {
		this.livelloAttuale = livelloAttuale;
	}

	public String getLivelloFinale() {
		return livelloFinale;
	}

	public void setLivelloFinale(String livelloFinale) {
		this.livelloFinale = livelloFinale;
	}

	public Boolean getDimissioni() {
		return dimissioni;
	}

	public void setDimissioni(Boolean dimissioni) {
		this.dimissioni = dimissioni;
	}

	public Boolean getPartTime() {
		return partTime;
	}

	public void setPartTime(Boolean partTime) {
		this.partTime = partTime;
	}

	public Boolean getPartTimeA() {
		return partTimeA;
	}

	public void setPartTimeA(Boolean partTimeA) {
		this.partTimeA = partTimeA;
	}

	public String getRetribuzioneMensileLorda() {
		return retribuzioneMensileLorda;
	}

	public void setRetribuzioneMensileLorda(String retribuzioneMensileLorda) {
		this.retribuzioneMensileLorda = retribuzioneMensileLorda;
	}

	public String getSuperminimoMensile() {
		return superminimoMensile;
	}

	public void setSuperminimoMensile(String superminimoMensile) {
		this.superminimoMensile = superminimoMensile;
	}

	public String getRalAnnua() {
		return ralAnnua;
	}

	public void setRalAnnua(String ralAnnua) {
		this.ralAnnua = ralAnnua;
	}

	public String getSuperminimoRal() {
		return superminimoRal;
	}

	public void setSuperminimoRal(String superminimoRal) {
		this.superminimoRal = superminimoRal;
	}

	public String getDiariaMese() {
		return diariaMese;
	}

	public void setDiariaMese(String diariaMese) {
		this.diariaMese = diariaMese;
	}

	public String getDiariaGg() {
		return diariaGg;
	}

	public void setDiariaGg(String diariaGg) {
		this.diariaGg = diariaGg;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getValoreTicket() {
		return valoreTicket;
	}

	public void setValoreTicket(String valoreTicket) {
		this.valoreTicket = valoreTicket;
	}

	public Boolean getCategoriaProtetta() {
		return categoriaProtetta;
	}

	public void setCategoriaProtetta(Boolean categoriaProtetta) {
		this.categoriaProtetta = categoriaProtetta;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getPfi() {
		return pfi;
	}

	public void setPfi(String pfi) {
		this.pfi = pfi;
	}

	public String getAssicurazioneObbligatoria() {
		return assicurazioneObbligatoria;
	}

	public void setAssicurazioneObbligatoria(String assicurazioneObbligatoria) {
		this.assicurazioneObbligatoria = assicurazioneObbligatoria;
	}

	public Date getCorsoSicurezza() {
		return corsoSicurezza;
	}

	public void setCorsoSicurezza(Date corsoSicurezza) {
		this.corsoSicurezza = corsoSicurezza;
	}

	public String getMotivazioneFineRapporto() {
		return motivazioneFineRapporto;
	}

	public void setMotivazioneFineRapporto(String motivazioneFineRapporto) {
		this.motivazioneFineRapporto = motivazioneFineRapporto;
	}

	public Boolean getPc() {
		return pc;
	}

	public void setPc(Boolean pc) {
		this.pc = pc;
	}

	public String getScattiAnzianita() {
		return scattiAnzianita;
	}

	public void setScattiAnzianita(String scattiAnzianita) {
		this.scattiAnzianita = scattiAnzianita;
	}

	public String getTariffaPartitaIva() {
		return tariffaPartitaIva;
	}

	public void setTariffaPartitaIva(String tariffaPartitaIva) {
		this.tariffaPartitaIva = tariffaPartitaIva;
	}

	public String getCanaleReclutamento() {
		return canaleReclutamento;
	}

	public void setCanaleReclutamento(String canaleReclutamento) {
		this.canaleReclutamento = canaleReclutamento;
	}

	public Contratto(TipoContratto tipoContratto, TipoAzienda tipoAzienda, ContrattoNazionale contrattoNazionale) {
		super();
		this.tipoContratto = tipoContratto;
		this.tipoAzienda = tipoAzienda;
		this.contrattoNazionale = contrattoNazionale;
	}

}
