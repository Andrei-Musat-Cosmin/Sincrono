package it.sincrono.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Contratto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToMany
	@JoinColumn(name = "id_tipo_contratto")
	private TipoContratto tipoContratto;
	@OneToMany
	@JoinColumn(name = "id_tipo_livello")
	private LivelloContratto livelloContratto;
	@OneToMany
	@JoinColumn(name = "id_tipo_societa")
	private TipoSocieta tipoSocieta;
	@OneToMany
	@JoinColumn(name = "id_contratto_nazionale")
	private ContrattoNazionale contarttoNazionale;
	@OneToMany
	@JoinColumn(name = "id_apprendistato")
	private Apprendistato apprendistato;
	@Column
	private Boolean attivo;

	@Column
	private String sede_assunzione;

	@Column
	private String qualifica;

	@Column // varchar->date
	private Date data_assunzione;

	@Column // varchar->date
	private Date data_inizio_prova;

	@Column // varchar->date
	private Date data_fine_prova;

	@Column // varchar->date
	private Date data_fine_rapporto;

	@Column // varchar->int
	private Integer mesi_durata;

	@Column // varchar->Integer
	private Integer livello_iniziale;

	@Column // varchar->Integer
	private Integer livello_attuale;

	@Column // varchar->Integer
	private Integer livello_finale;

	@Column
	private Boolean dimissioni;

	@Column
	private String part_time;

	@Column
	private String part_timeA;

	@Column
	private String retribuzione_mensile_lorda;

	@Column
	private String superminimo_mensile;

	@Column
	private String ral_annua;

	@Column
	private String superminimo_ral;

	@Column
	private String diaria_mese;

	@Column
	private String diaria_gg;

	@Column
	private String ticket;

	@Column
	private String valore_ticket;

	@Column
	private String categoria_protetta;

	@Column
	private String tutor;

	@Column
	private String pfi;

	@Column
	private String assicurazione_obbligatoria;

	@Column
	private Date corso_sicurezza;

	@Column
	private String motivazione_fine_rapporto;

	@Column
	private Boolean pc;

	@Column
	private String scatti_anzianita;

	@Column
	private String tariffa_partita_iva;

	@Column
	private String canale_reclutamento;

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

	public TipoSocieta getTipoSocieta() {
		return tipoSocieta;
	}

	public void setTipoSocieta(TipoSocieta tipoSocieta) {
		this.tipoSocieta = tipoSocieta;
	}

	public ContrattoNazionale getContarttoNazionale() {
		return contarttoNazionale;
	}

	public void setContarttoNazionale(ContrattoNazionale contarttoNazionale) {
		this.contarttoNazionale = contarttoNazionale;
	}

	public Apprendistato getApprendistato() {
		return apprendistato;
	}

	public void setApprendistato(Apprendistato apprendistato) {
		this.apprendistato = apprendistato;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public String getSede_assunzione() {
		return sede_assunzione;
	}

	public void setSede_assunzione(String sede_assunzione) {
		this.sede_assunzione = sede_assunzione;
	}

	public String getQualifica() {
		return qualifica;
	}

	public void setQualifica(String qualifica) {
		this.qualifica = qualifica;
	}

	public Date getData_assunzione() {
		return data_assunzione;
	}

	public void setData_assunzione(Date data_assunzione) {
		this.data_assunzione = data_assunzione;
	}

	public Date getData_inizio_prova() {
		return data_inizio_prova;
	}

	public void setData_inizio_prova(Date data_inizio_prova) {
		this.data_inizio_prova = data_inizio_prova;
	}

	public Date getData_fine_prova() {
		return data_fine_prova;
	}

	public void setData_fine_prova(Date data_fine_prova) {
		this.data_fine_prova = data_fine_prova;
	}

	public Date getData_fine_rapporto() {
		return data_fine_rapporto;
	}

	public void setData_fine_rapporto(Date data_fine_rapporto) {
		this.data_fine_rapporto = data_fine_rapporto;
	}

	public Integer getMesi_durata() {
		return mesi_durata;
	}

	public void setMesi_durata(Integer mesi_durata) {
		this.mesi_durata = mesi_durata;
	}

	public Integer getLivello_iniziale() {
		return livello_iniziale;
	}

	public void setLivello_iniziale(Integer livello_iniziale) {
		this.livello_iniziale = livello_iniziale;
	}

	public Integer getLivello_attuale() {
		return livello_attuale;
	}

	public void setLivello_attuale(Integer livello_attuale) {
		this.livello_attuale = livello_attuale;
	}

	public Integer getLivello_finale() {
		return livello_finale;
	}

	public void setLivello_finale(Integer livello_finale) {
		this.livello_finale = livello_finale;
	}

	public Boolean getDimissioni() {
		return dimissioni;
	}

	public void setDimissioni(Boolean dimissioni) {
		this.dimissioni = dimissioni;
	}

	public String getPart_time() {
		return part_time;
	}

	public void setPart_time(String part_time) {
		this.part_time = part_time;
	}

	public String getPart_timeA() {
		return part_timeA;
	}

	public void setPart_timeA(String part_timeA) {
		this.part_timeA = part_timeA;
	}

	public String getRetribuzione_mensile_lorda() {
		return retribuzione_mensile_lorda;
	}

	public void setRetribuzione_mensile_lorda(String retribuzione_mensile_lorda) {
		this.retribuzione_mensile_lorda = retribuzione_mensile_lorda;
	}

	public String getSuperminimo_mensile() {
		return superminimo_mensile;
	}

	public void setSuperminimo_mensile(String superminimo_mensile) {
		this.superminimo_mensile = superminimo_mensile;
	}

	public String getRal_annua() {
		return ral_annua;
	}

	public void setRal_annua(String ral_annua) {
		this.ral_annua = ral_annua;
	}

	public String getSuperminimo_ral() {
		return superminimo_ral;
	}

	public void setSuperminimo_ral(String superminimo_ral) {
		this.superminimo_ral = superminimo_ral;
	}

	public String getDiaria_mese() {
		return diaria_mese;
	}

	public void setDiaria_mese(String diaria_mese) {
		this.diaria_mese = diaria_mese;
	}

	public String getDiaria_gg() {
		return diaria_gg;
	}

	public void setDiaria_gg(String diaria_gg) {
		this.diaria_gg = diaria_gg;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getValore_ticket() {
		return valore_ticket;
	}

	public void setValore_ticket(String valore_ticket) {
		this.valore_ticket = valore_ticket;
	}

	public String getCategoria_protetta() {
		return categoria_protetta;
	}

	public void setCategoria_protetta(String categoria_protetta) {
		this.categoria_protetta = categoria_protetta;
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

	public String getAssicurazione_obbligatoria() {
		return assicurazione_obbligatoria;
	}

	public void setAssicurazione_obbligatoria(String assicurazione_obbligatoria) {
		this.assicurazione_obbligatoria = assicurazione_obbligatoria;
	}

	public Date getCorso_sicurezza() {
		return corso_sicurezza;
	}

	public void setCorso_sicurezza(Date corso_sicurezza) {
		this.corso_sicurezza = corso_sicurezza;
	}

	public String getMotivazione_fine_rapporto() {
		return motivazione_fine_rapporto;
	}

	public void setMotivazione_fine_rapporto(String motivazione_fine_rapporto) {
		this.motivazione_fine_rapporto = motivazione_fine_rapporto;
	}

	public Boolean getPc() {
		return pc;
	}

	public void setPc(Boolean pc) {
		this.pc = pc;
	}

	public String getScatti_anzianita() {
		return scatti_anzianita;
	}

	public void setScatti_anzianita(String scatti_anzianita) {
		this.scatti_anzianita = scatti_anzianita;
	}

	public String getTariffa_partita_iva() {
		return tariffa_partita_iva;
	}

	public void setTariffa_partita_iva(String tariffa_partita_iva) {
		this.tariffa_partita_iva = tariffa_partita_iva;
	}

	public String getCanale_reclutamento() {
		return canale_reclutamento;
	}

	public void setCanale_reclutamento(String canale_reclutamento) {
		this.canale_reclutamento = canale_reclutamento;
	}

	public Contratto(Integer id, TipoContratto tipoContratto, LivelloContratto livelloContratto,
			TipoSocieta tipoSocieta, ContrattoNazionale contarttoNazionale, Apprendistato apprendistato, Boolean attivo,
			String sede_assunzione, String qualifica, Date data_assunzione, Date data_inizio_prova,
			Date data_fine_prova, Date data_fine_rapporto, Integer mesi_durata, Integer livello_iniziale,
			Integer livello_attuale, Integer livello_finale, Boolean dimissioni, String part_time, String part_timeA,
			String retribuzione_mensile_lorda, String superminimo_mensile, String ral_annua, String superminimo_ral,
			String diaria_mese, String diaria_gg, String ticket, String valore_ticket, String categoria_protetta,
			String tutor, String pfi, String assicurazione_obbligatoria, Date corso_sicurezza,
			String motivazione_fine_rapporto, Boolean pc, String scatti_anzianita, String tariffa_partita_iva,
			String canale_reclutamento) {
		super();
		this.id = id;
		this.tipoContratto = tipoContratto;
		this.livelloContratto = livelloContratto;
		this.tipoSocieta = tipoSocieta;
		this.contarttoNazionale = contarttoNazionale;
		this.apprendistato = apprendistato;
		this.attivo = attivo;
		this.sede_assunzione = sede_assunzione;
		this.qualifica = qualifica;
		this.data_assunzione = data_assunzione;
		this.data_inizio_prova = data_inizio_prova;
		this.data_fine_prova = data_fine_prova;
		this.data_fine_rapporto = data_fine_rapporto;
		this.mesi_durata = mesi_durata;
		this.livello_iniziale = livello_iniziale;
		this.livello_attuale = livello_attuale;
		this.livello_finale = livello_finale;
		this.dimissioni = dimissioni;
		this.part_time = part_time;
		this.part_timeA = part_timeA;
		this.retribuzione_mensile_lorda = retribuzione_mensile_lorda;
		this.superminimo_mensile = superminimo_mensile;
		this.ral_annua = ral_annua;
		this.superminimo_ral = superminimo_ral;
		this.diaria_mese = diaria_mese;
		this.diaria_gg = diaria_gg;
		this.ticket = ticket;
		this.valore_ticket = valore_ticket;
		this.categoria_protetta = categoria_protetta;
		this.tutor = tutor;
		this.pfi = pfi;
		this.assicurazione_obbligatoria = assicurazione_obbligatoria;
		this.corso_sicurezza = corso_sicurezza;
		this.motivazione_fine_rapporto = motivazione_fine_rapporto;
		this.pc = pc;
		this.scatti_anzianita = scatti_anzianita;
		this.tariffa_partita_iva = tariffa_partita_iva;
		this.canale_reclutamento = canale_reclutamento;
	}

	public Contratto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
