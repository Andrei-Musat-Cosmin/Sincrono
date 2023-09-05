package it.sincrono.entities;

import java.util.Date;
import java.util.Objects;

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
	@JoinColumn(name = "id_tipo_motivazione_fine_rapporto")
	private TipoMotivazioneFineRapporto tipoMotivazioneFineRapporto;

	@ManyToOne
	@JoinColumn(name = "id_tipo_canale_reclutamento")
	private TipoCanaleReclutamento tipoCanaleReclutamento;

	@ManyToOne
	@JoinColumn(name = "id_tipo_contratto")
	private TipoContratto tipoContratto;

	@ManyToOne
	@JoinColumn(name = "id_tipo_livello")
	private TipoLivelloContratto livelloContratto;

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

	@Column(name = "mesi_durata")
	private Integer mesiDurata;

	@Column(name = "livello_attuale")
	private String livelloAttuale;

	@Column(name = "livello_finale")
	private String livelloFinale;

	@Column(name = "dimissioni")
	private Boolean dimissioni;

	@Column(name = "part_time")
	private Boolean partTime;

	@Column(name = "percentuale_part_time")
	private Integer percentualePartTime;

	@Column(name = "retribuzione_mensile_lorda")
	private String retribuzioneMensileLorda;

	@Column(name = "superminimo_mensile")
	private String superminimoMensile;

	@Column(name = "ral_annua")
	private String ralAnnua;

	@Column(name = "superminimo_ral")
	private String superminimoRal;

	@Column(name = "diaria_mese")
	private String diariaMesile;

	@Column(name = "diaria_gg")
	private String diariaGiornaliera;

	@Column(name = "ticket")
	private Boolean ticket;

	@Column(name = "valore_ticket")
	private String valoreTicket;

	@Column(name = "categoria_protetta")
	private Boolean categoriaProtetta;

	@Column(name = "tutor")
	private String tutor;

	@Column(name = "pfi")
	private String pfi;

	@Column(name = "corso_sicurezza")
	private Date corsoSicurezza;

	@Column(name = "pc")
	private Boolean pc;

	@Column(name = "data_visita_medica")
	private Date dataVisitaMedica;

	@Column(name = "scatti_anzianita")
	private Integer scattiAnzianita;

	@Column(name = "tariffa_partita_iva")
	private String tariffaPartitaIva;

	@Column(name = "assicurazione_obbligatoria")
	private String assicurazioneObbligatoria;

	@Column(name = "retribuzione_netta_giornaliera")
	private Integer retribuzioneNettaGiornaliera;

	@Column(name = "retribuzione_netta_mensile")
	private Integer retribuzioneNettaMensile;

	public Contratto(Integer id, TipoMotivazioneFineRapporto tipoMotivazioneFineRapporto,
			TipoCanaleReclutamento tipoCanaleReclutamento, TipoContratto tipoContratto,
			TipoLivelloContratto livelloContratto, TipoAzienda tipoAzienda, TipoCcnl tipoCcnl, Boolean attivo,
			String qualifica, String sedeAssunzione, Date dataAssunzione, Date dataInizioProva, Date dataFineProva,
			Date dataFineRapporto, Integer mesiDurata, String livelloAttuale, String livelloFinale, Boolean dimissioni,
			Boolean partTime, Integer percentualePartTime, String retribuzioneMensileLorda, String superminimoMensile,
			String ralAnnua, String superminimoRal, String diariaMesile, String diariaGiornaliera, Boolean ticket,
			String valoreTicket, Boolean categoriaProtetta, String tutor, String pfi, Date corsoSicurezza, Boolean pc,
			Date dataVisitaMedica, Integer scattiAnzianita, String tariffaPartitaIva, String assicurazioneObbligatoria,
			Integer retribuzioneNettaGiornaliera, Integer retribuzioneNettaMensile) {
		super();
		this.id = id;
		this.tipoMotivazioneFineRapporto = tipoMotivazioneFineRapporto;
		this.tipoCanaleReclutamento = tipoCanaleReclutamento;
		this.tipoContratto = tipoContratto;
		this.livelloContratto = livelloContratto;
		this.tipoAzienda = tipoAzienda;
		this.tipoCcnl = tipoCcnl;
		this.attivo = attivo;
		this.qualifica = qualifica;
		this.sedeAssunzione = sedeAssunzione;
		this.dataAssunzione = dataAssunzione;
		this.dataInizioProva = dataInizioProva;
		this.dataFineProva = dataFineProva;
		this.dataFineRapporto = dataFineRapporto;
		this.mesiDurata = mesiDurata;
		this.livelloAttuale = livelloAttuale;
		this.livelloFinale = livelloFinale;
		this.dimissioni = dimissioni;
		this.partTime = partTime;
		this.percentualePartTime = percentualePartTime;
		this.retribuzioneMensileLorda = retribuzioneMensileLorda;
		this.superminimoMensile = superminimoMensile;
		this.ralAnnua = ralAnnua;
		this.superminimoRal = superminimoRal;
		this.diariaMesile = diariaMesile;
		this.diariaGiornaliera = diariaGiornaliera;
		this.ticket = ticket;
		this.valoreTicket = valoreTicket;
		this.categoriaProtetta = categoriaProtetta;
		this.tutor = tutor;
		this.pfi = pfi;
		this.corsoSicurezza = corsoSicurezza;
		this.pc = pc;
		this.dataVisitaMedica = dataVisitaMedica;
		this.scattiAnzianita = scattiAnzianita;
		this.tariffaPartitaIva = tariffaPartitaIva;
		this.assicurazioneObbligatoria = assicurazioneObbligatoria;
		this.retribuzioneNettaGiornaliera = retribuzioneNettaGiornaliera;
		this.retribuzioneNettaMensile = retribuzioneNettaMensile;
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
				&& Objects.equals(attivo, other.attivo)
				&& Objects.equals(tipoCanaleReclutamento, other.tipoCanaleReclutamento)
				&& Objects.equals(categoriaProtetta, other.categoriaProtetta)
				&& Objects.equals(corsoSicurezza, other.corsoSicurezza)
				&& Objects.equals(dataAssunzione, other.dataAssunzione)
				&& Objects.equals(dataFineProva, other.dataFineProva)
				&& Objects.equals(dataFineRapporto, other.dataFineRapporto)
				&& Objects.equals(dataInizioProva, other.dataInizioProva)
				&& Objects.equals(dataVisitaMedica, other.dataVisitaMedica)
				&& Objects.equals(diariaGiornaliera, other.diariaGiornaliera)
				&& Objects.equals(diariaMesile, other.diariaMesile) && Objects.equals(dimissioni, other.dimissioni)
				&& Objects.equals(id, other.id) && Objects.equals(livelloAttuale, other.livelloAttuale)
				&& Objects.equals(livelloContratto, other.livelloContratto)
				&& Objects.equals(livelloFinale, other.livelloFinale) && Objects.equals(mesiDurata, other.mesiDurata)
				&& Objects.equals(tipoMotivazioneFineRapporto, other.tipoMotivazioneFineRapporto)
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
				&& Objects.equals(tipoAzienda, other.tipoAzienda) && Objects.equals(tipoCcnl, other.tipoCcnl)
				&& Objects.equals(tipoContratto, other.tipoContratto) && Objects.equals(tutor, other.tutor)
				&& Objects.equals(valoreTicket, other.valoreTicket);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoMotivazioneFineRapporto getTipoMotivazioneFineRapporto() {
		return tipoMotivazioneFineRapporto;
	}

	public void setTipoMotivazioneFineRapporto(TipoMotivazioneFineRapporto tipoMotivazioneFineRapporto) {
		this.tipoMotivazioneFineRapporto = tipoMotivazioneFineRapporto;
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

	public TipoLivelloContratto getLivelloContratto() {
		return livelloContratto;
	}

	public void setLivelloContratto(TipoLivelloContratto livelloContratto) {
		this.livelloContratto = livelloContratto;
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

	public Integer getPercentualePartTime() {
		return percentualePartTime;
	}

	public void setPercentualePartTime(Integer percentualePartTime) {
		this.percentualePartTime = percentualePartTime;
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

	public String getDiariaMesile() {
		return diariaMesile;
	}

	public void setDiariaMesile(String diariaMesile) {
		this.diariaMesile = diariaMesile;
	}

	public String getDiariaGiornaliera() {
		return diariaGiornaliera;
	}

	public void setDiariaGiornaliera(String diariaGiornaliera) {
		this.diariaGiornaliera = diariaGiornaliera;
	}

	public Boolean getTicket() {
		return ticket;
	}

	public void setTicket(Boolean ticket) {
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

	public Date getCorsoSicurezza() {
		return corsoSicurezza;
	}

	public void setCorsoSicurezza(Date corsoSicurezza) {
		this.corsoSicurezza = corsoSicurezza;
	}

	public Boolean getPc() {
		return pc;
	}

	public void setPc(Boolean pc) {
		this.pc = pc;
	}

	public Date getDataVisitaMedica() {
		return dataVisitaMedica;
	}

	public void setDataVisitaMedica(Date dataVisitaMedica) {
		this.dataVisitaMedica = dataVisitaMedica;
	}

	public Integer getScattiAnzianita() {
		return scattiAnzianita;
	}

	public void setScattiAnzianita(Integer scattiAnzianita) {
		this.scattiAnzianita = scattiAnzianita;
	}

	public String getTariffaPartitaIva() {
		return tariffaPartitaIva;
	}

	public void setTariffaPartitaIva(String tariffaPartitaIva) {
		this.tariffaPartitaIva = tariffaPartitaIva;
	}

	public String getAssicurazioneObbligatoria() {
		return assicurazioneObbligatoria;
	}

	public void setAssicurazioneObbligatoria(String assicurazioneObbligatoria) {
		this.assicurazioneObbligatoria = assicurazioneObbligatoria;
	}

	public Integer getRetribuzioneNettaGiornaliera() {
		return retribuzioneNettaGiornaliera;
	}

	public void setRetribuzioneNettaGiornaliera(Integer retribuzioneNettaGiornaliera) {
		this.retribuzioneNettaGiornaliera = retribuzioneNettaGiornaliera;
	}

	public Integer getRetribuzioneNettaMensile() {
		return retribuzioneNettaMensile;
	}

	public void setRetribuzioneNettaMensile(Integer retribuzioneNettaMensile) {
		this.retribuzioneNettaMensile = retribuzioneNettaMensile;
	}

}
