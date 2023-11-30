package it.sincrono.entities;

import java.util.Date;
import java.util.Objects;

import it.sincrono.services.utils.DateUtil;
import it.sincrono.services.utils.TipologicheCompareContratto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "contratto")
public class Contratto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_tipo_causa_fine_rapporto")
	private TipoCausaFineRapporto tipoCausaFineRapporto;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_causa_fine_contratto")
	private TipoCausaFineContratto tipoCausaFineContratto;

	@ManyToOne
	@JoinColumn(name = "id_tipo_canale_reclutamento")
	private TipoCanaleReclutamento tipoCanaleReclutamento;

	@ManyToOne
	@JoinColumn(name = "id_tipo_contratto")
	private TipoContratto tipoContratto;

	@ManyToOne
	@JoinColumn(name = "id_tipo_livello")
	private TipoLivelloContratto tipoLivelloContratto;

	@ManyToOne
	@JoinColumn(name = "id_tipo_azienda")
	private TipoAzienda tipoAzienda;

	@ManyToOne
	@JoinColumn(name = "id_tipo_ccnl")
	private TipoCcnl tipoCcnl;

	@Column(name = "attivo")
	private Boolean attivo;

	@Column(name = "qualifica")
	private String qualifica;

	@Column(name = "sede_assunzione")
	private String sedeAssunzione;

	@Column(name = "data_assunzione")
	private Date dataAssunzione;

	@Column(name = "data_inizio_prova")
	private Date dataInizioProva;

	@Column(name = "data_fine_prova")
	private Date dataFineProva;

	@Column(name = "data_fine_rapporto")
	private Date dataFineRapporto;
	
	@Column(name = "data_fine_contratto")
	private Date dataFineContratto;

	@Column(name = "mesi_durata")
	private Integer mesiDurata;

	@Column(name = "livello_attuale")
	private String livelloAttuale;

	@Column(name = "livello_finale")
	private String livelloFinale;

	@Column(name = "part_time")
	private Boolean partTime;

	@Column(name = "percentuale_part_time")
	private Double percentualePartTime;

	@Column(name = "retribuzione_mensile_lorda")
	private Double retribuzioneMensileLorda;

	@Column(name = "superminimo_mensile")
	private Double superminimoMensile;

	@Column(name = "ral_annua")
	private Double ralAnnua;

	@Column(name = "superminimo_ral")
	private Double superminimoRal;

	@Column(name = "diaria_mensile")
	private Double diariaMensile;

	@Column(name = "diaria_giornaliera")
	private Double diariaGiornaliera;

	@Column(name = "ticket")
	private Boolean ticket;

	@Column(name = "valore_ticket")
	private Double valoreTicket;

	@Column(name = "tutor")
	private String tutor;

	@Column(name = "pfi")
	private Boolean pfi;

	@Column(name = "corso_sicurezza")
	private Boolean corsoSicurezza;

	@Column(name = "data_corso_sicurezza")
	private Date dataCorsoSicurezza;

	@Column(name = "pc")
	private Boolean pc;

	@Column(name = "visita_medica")
	private Boolean visitaMedica;

	@Column(name = "data_visita_medica")
	private Date dataVisitaMedica;

	@Column(name = "scatti_anzianita")
	private Double scattiAnzianita;

	@Column(name = "tariffa_partita_iva")
	private Double tariffaPartitaIva;

	@Column(name = "assicurazione_obbligatoria")
	private Boolean assicurazioneObbligatoria;

	@Column(name = "retribuzione_netta_giornaliera")
	private Double retribuzioneNettaGiornaliera;

	@Column(name = "retribuzione_netta_mensile")
	private Double retribuzioneNettaMensile;

	@Column(name = "diaria_annua")
	private Double diariaAnnua;

	@Transient
	private Double RalPartTime;

	
	

	public Contratto(Integer id, TipoCausaFineRapporto tipoCausaFineRapporto,
			TipoCausaFineContratto tipoCausaFineContratto, TipoCanaleReclutamento tipoCanaleReclutamento,
			TipoContratto tipoContratto, TipoLivelloContratto tipoLivelloContratto, TipoAzienda tipoAzienda,
			TipoCcnl tipoCcnl, Boolean attivo, String qualifica, String sedeAssunzione, Date dataAssunzione,
			Date dataInizioProva, Date dataFineProva, Date dataFineRapporto, Date dataFineContratto, Integer mesiDurata,
			String livelloAttuale, String livelloFinale, Boolean partTime, Double percentualePartTime,
			Double retribuzioneMensileLorda, Double superminimoMensile, Double ralAnnua, Double superminimoRal,
			Double diariaMensile, Double diariaGiornaliera, Boolean ticket, Double valoreTicket, String tutor,
			Boolean pfi, Boolean corsoSicurezza, Date dataCorsoSicurezza, Boolean pc, Boolean visitaMedica,
			Date dataVisitaMedica, Double scattiAnzianita, Double tariffaPartitaIva, Boolean assicurazioneObbligatoria,
			Double retribuzioneNettaGiornaliera, Double retribuzioneNettaMensile, Double diariaAnnua,
			Double ralPartTime) {
		super();
		this.id = id;
		this.tipoCausaFineRapporto = tipoCausaFineRapporto;
		this.tipoCausaFineContratto = tipoCausaFineContratto;
		this.tipoCanaleReclutamento = tipoCanaleReclutamento;
		this.tipoContratto = tipoContratto;
		this.tipoLivelloContratto = tipoLivelloContratto;
		this.tipoAzienda = tipoAzienda;
		this.tipoCcnl = tipoCcnl;
		this.attivo = attivo;
		this.qualifica = qualifica;
		this.sedeAssunzione = sedeAssunzione;
		this.dataAssunzione = dataAssunzione;
		this.dataInizioProva = dataInizioProva;
		this.dataFineProva = dataFineProva;
		this.dataFineRapporto = dataFineRapporto;
		this.dataFineContratto = dataFineContratto;
		this.mesiDurata = mesiDurata;
		this.livelloAttuale = livelloAttuale;
		this.livelloFinale = livelloFinale;
		this.partTime = partTime;
		this.percentualePartTime = percentualePartTime;
		this.retribuzioneMensileLorda = retribuzioneMensileLorda;
		this.superminimoMensile = superminimoMensile;
		this.ralAnnua = ralAnnua;
		this.superminimoRal = superminimoRal;
		this.diariaMensile = diariaMensile;
		this.diariaGiornaliera = diariaGiornaliera;
		this.ticket = ticket;
		this.valoreTicket = valoreTicket;
		this.tutor = tutor;
		this.pfi = pfi;
		this.corsoSicurezza = corsoSicurezza;
		this.dataCorsoSicurezza = dataCorsoSicurezza;
		this.pc = pc;
		this.visitaMedica = visitaMedica;
		this.dataVisitaMedica = dataVisitaMedica;
		this.scattiAnzianita = scattiAnzianita;
		this.tariffaPartitaIva = tariffaPartitaIva;
		this.assicurazioneObbligatoria = assicurazioneObbligatoria;
		this.retribuzioneNettaGiornaliera = retribuzioneNettaGiornaliera;
		this.retribuzioneNettaMensile = retribuzioneNettaMensile;
		this.diariaAnnua = diariaAnnua;
		RalPartTime = ralPartTime;
	}

	public Contratto(TipoCanaleReclutamento tipoCanaleReclutamento, TipoContratto tipoContratto,
			TipoAzienda tipoAzienda, TipoCcnl tipoCcnl) {
		super();
		this.tipoCanaleReclutamento = tipoCanaleReclutamento;
		this.tipoContratto = tipoContratto;
		this.tipoAzienda = tipoAzienda;
		this.tipoCcnl = tipoCcnl;
	}

	public Contratto(Integer id) {
		this.id = id;
	}

	public Contratto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contratto other = (Contratto) obj;
		return Objects.equals(assicurazioneObbligatoria, other.assicurazioneObbligatoria)
				&& Objects.equals(corsoSicurezza, other.corsoSicurezza)
				&& DateUtil.dateCompare(dataAssunzione, other.dataAssunzione)
				&& DateUtil.dateCompare(dataCorsoSicurezza, other.dataCorsoSicurezza)
				&& DateUtil.dateCompare(dataFineProva, other.dataFineProva)
				&& DateUtil.dateCompare(dataFineRapporto, other.dataFineRapporto)
				&& DateUtil.dateCompare(dataInizioProva, other.dataInizioProva)
				&& DateUtil.dateCompare(dataVisitaMedica, other.dataVisitaMedica)
				&& Objects.equals(diariaAnnua, other.diariaAnnua)
				&& Objects.equals(diariaGiornaliera, other.diariaGiornaliera)
				&& Objects.equals(diariaMensile, other.diariaMensile) && Objects.equals(id, other.id)
				&& Objects.equals(livelloAttuale, other.livelloAttuale)
				&& Objects.equals(livelloFinale, other.livelloFinale) && Objects.equals(mesiDurata, other.mesiDurata)
				&& Objects.equals(partTime, other.partTime) && Objects.equals(pc, other.pc)
				&& Objects.equals(percentualePartTime, other.percentualePartTime) && Objects.equals(pfi, other.pfi)
				&& Objects.equals(qualifica, other.qualifica) && Objects.equals(ralAnnua, other.ralAnnua)
				&& Objects.equals(retribuzioneMensileLorda, other.retribuzioneMensileLorda)
				&& Objects.equals(retribuzioneNettaGiornaliera, other.retribuzioneNettaGiornaliera)
				&& Objects.equals(retribuzioneNettaMensile, other.retribuzioneNettaMensile)
				&& Objects.equals(scattiAnzianita, other.scattiAnzianita)
				&& Objects.equals(sedeAssunzione, other.sedeAssunzione)
				&& Objects.equals(superminimoMensile, other.superminimoMensile)
				&& Objects.equals(superminimoRal, other.superminimoRal)
				&& Objects.equals(tariffaPartitaIva, other.tariffaPartitaIva) && Objects.equals(ticket, other.ticket)
				&& TipologicheCompareContratto.tipologicheCompare(this, other) && Objects.equals(tutor, other.tutor)
				&& Objects.equals(valoreTicket, other.valoreTicket) && Objects.equals(visitaMedica, other.visitaMedica);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoCausaFineRapporto getTipoCausaFineRapporto() {
		return tipoCausaFineRapporto;
	}

	public void setTipoCausaFineRapporto(TipoCausaFineRapporto tipoCausaFineRapporto) {
		this.tipoCausaFineRapporto = tipoCausaFineRapporto;
	}

	public TipoCanaleReclutamento getTipoCanaleReclutamento() {
		return tipoCanaleReclutamento;
	}

	public void setTipoCanaleReclutamento(TipoCanaleReclutamento tipoCanaleReclutamento) {
		this.tipoCanaleReclutamento = tipoCanaleReclutamento;
	}

	public TipoContratto getTipoContratto() {
		return tipoContratto;
	}

	public void setTipoContratto(TipoContratto tipoContratto) {
		this.tipoContratto = tipoContratto;
	}

	public TipoLivelloContratto getTipoLivelloContratto() {
		return tipoLivelloContratto;
	}

	public void setTipoLivelloContratto(TipoLivelloContratto tipoLivelloContratto) {
		this.tipoLivelloContratto = tipoLivelloContratto;
	}

	public TipoAzienda getTipoAzienda() {
		return tipoAzienda;
	}

	public void setTipoAzienda(TipoAzienda tipoAzienda) {
		this.tipoAzienda = tipoAzienda;
	}

	public TipoCcnl getTipoCcnl() {
		return tipoCcnl;
	}

	public void setTipoCcnl(TipoCcnl tipoCcnl) {
		this.tipoCcnl = tipoCcnl;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public String getQualifica() {
		return qualifica;
	}

	public void setQualifica(String qualifica) {
		this.qualifica = qualifica;
	}

	public String getSedeAssunzione() {
		return sedeAssunzione;
	}

	public void setSedeAssunzione(String sedeAssunzione) {
		this.sedeAssunzione = sedeAssunzione;
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

	public Boolean getPartTime() {
		return partTime;
	}

	public void setPartTime(Boolean partTime) {
		this.partTime = partTime;
	}

	public Double getPercentualePartTime() {
		return percentualePartTime;
	}

	public void setPercentualePartTime(Double percentualePartTime) {
		this.percentualePartTime = percentualePartTime;
	}

	public Double getRetribuzioneMensileLorda() {
		return retribuzioneMensileLorda;
	}

	public void setRetribuzioneMensileLorda(Double retribuzioneMensileLorda) {
		this.retribuzioneMensileLorda = retribuzioneMensileLorda;
	}

	public Double getSuperminimoMensile() {
		return superminimoMensile;
	}

	public void setSuperminimoMensile(Double superminimoMensile) {
		this.superminimoMensile = superminimoMensile;
	}

	public Double getRalAnnua() {
		return ralAnnua;
	}

	public void setRalAnnua(Double ralAnnua) {
		this.ralAnnua = ralAnnua;
	}

	public Double getSuperminimoRal() {
		return superminimoRal;
	}

	public void setSuperminimoRal(Double superminimoRal) {
		this.superminimoRal = superminimoRal;
	}

	public Double getDiariaMensile() {
		return diariaMensile;
	}

	public void setDiariaMensile(Double diariaMensile) {
		this.diariaMensile = diariaMensile;
	}

	public Double getDiariaGiornaliera() {
		return diariaGiornaliera;
	}

	public void setDiariaGiornaliera(Double diariaGiornaliera) {
		this.diariaGiornaliera = diariaGiornaliera;
	}

	public Boolean getTicket() {
		return ticket;
	}

	public void setTicket(Boolean ticket) {
		this.ticket = ticket;
	}

	public Double getValoreTicket() {
		return valoreTicket;
	}

	public void setValoreTicket(Double valoreTicket) {
		this.valoreTicket = valoreTicket;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public Boolean getPfi() {
		return pfi;
	}

	public void setPfi(Boolean pfi) {
		this.pfi = pfi;
	}

	public Boolean getCorsoSicurezza() {
		return corsoSicurezza;
	}

	public void setCorsoSicurezza(Boolean corsoSicurezza) {
		this.corsoSicurezza = corsoSicurezza;
	}

	public Date getDataCorsoSicurezza() {
		return dataCorsoSicurezza;
	}

	public void setDataCorsoSicurezza(Date dataCorsoSicurezza) {
		this.dataCorsoSicurezza = dataCorsoSicurezza;
	}

	public Boolean getPc() {
		return pc;
	}

	public void setPc(Boolean pc) {
		this.pc = pc;
	}

	public Boolean getVisitaMedica() {
		return visitaMedica;
	}

	public void setVisitaMedica(Boolean visitaMedica) {
		this.visitaMedica = visitaMedica;
	}

	public Date getDataVisitaMedica() {
		return dataVisitaMedica;
	}

	public void setDataVisitaMedica(Date dataVisitaMedica) {
		this.dataVisitaMedica = dataVisitaMedica;
	}

	public Double getScattiAnzianita() {
		return scattiAnzianita;
	}

	public void setScattiAnzianita(Double scattiAnzianita) {
		this.scattiAnzianita = scattiAnzianita;
	}

	public Double getTariffaPartitaIva() {
		return tariffaPartitaIva;
	}

	public void setTariffaPartitaIva(Double tariffaPartitaIva) {
		this.tariffaPartitaIva = tariffaPartitaIva;
	}

	public Boolean getAssicurazioneObbligatoria() {
		return assicurazioneObbligatoria;
	}

	public void setAssicurazioneObbligatoria(Boolean assicurazioneObbligatoria) {
		this.assicurazioneObbligatoria = assicurazioneObbligatoria;
	}

	public Double getRetribuzioneNettaGiornaliera() {
		return retribuzioneNettaGiornaliera;
	}

	public void setRetribuzioneNettaGiornaliera(Double retribuzioneNettaGiornaliera) {
		this.retribuzioneNettaGiornaliera = retribuzioneNettaGiornaliera;
	}

	public Double getRetribuzioneNettaMensile() {
		return retribuzioneNettaMensile;
	}

	public void setRetribuzioneNettaMensile(Double retribuzioneNettaMensile) {
		this.retribuzioneNettaMensile = retribuzioneNettaMensile;
	}

	public Double getDiariaAnnua() {
		return diariaAnnua;
	}

	public void setDiariaAnnua(Double diariaAnnua) {
		this.diariaAnnua = diariaAnnua;
	}

	public Double getRalPartTime() {

		return this.RalPartTime;

	}

	public void setRalPartTime(Double ralPartTime) {

		this.RalPartTime = ralPartTime;

	}

	public TipoCausaFineContratto getTipoCausaFineContratto() {
		return tipoCausaFineContratto;
	}

	public void setTipoCausaFineContratto(TipoCausaFineContratto tipoCausaFineContratto) {
		this.tipoCausaFineContratto = tipoCausaFineContratto;
	}

	public Date getDataFineContratto() {
		return dataFineContratto;
	}

	public void setDataFineContratto(Date dataFineContratto) {
		this.dataFineContratto = dataFineContratto;
	}
	
	

}
