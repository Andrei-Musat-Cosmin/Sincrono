package it.sincrono.entities;

import java.util.Date;
import java.util.Objects;

import it.sincrono.services.utils.DateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "commessa")
public class Commessa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "azienda_cliente")
	private String aziendaCliente;

	@Column(name = "cliente_finale")
	private String clienteFinale;

	@Column(name = "titolo_posizione")
	private String titoloPosizione;

	@Column(name = "distacco")
	private Boolean distacco;

	@Column(name = "distacco_azienda")
	private String distaccoAzienda;

	@Column(name = "distacco_data")
	private Date distaccoData;

	@Column(name = "data_inizio")
	private Date dataInizio;

	@Column(name = "data_fine")
	private Date dataFine;

	@Column(name = "tariffa_giornaliera")
	private String tariffaGiornaliera;

	@Column(name = "azienda_di_fatturazione_interna")
	private String aziendaDiFatturazioneInterna;

	@Column(name = "attivo")
	private Boolean attivo;


	public Commessa(Integer id, String aziendaCliente, String clienteFinale, String titoloPosizione, Boolean distacco,
			Date distaccoData, String distaccoAzienda, Date dataInizio, Date dataFine, String tariffaGiornaliera,
			String aziendaDiFatturazioneInterna, Boolean attivo) {
		super();
		this.id = id;
		this.aziendaCliente = aziendaCliente;
		this.clienteFinale = clienteFinale;
		this.titoloPosizione = titoloPosizione;
		this.distacco = distacco;
		this.distaccoAzienda = distaccoAzienda;
		this.distaccoData = distaccoData;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tariffaGiornaliera = tariffaGiornaliera;
		this.aziendaDiFatturazioneInterna = aziendaDiFatturazioneInterna;
		this.attivo = attivo;

	}

	public Commessa() {
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
		Commessa other = (Commessa) obj;
		return Objects.equals(aziendaDiFatturazioneInterna, other.aziendaDiFatturazioneInterna)
				&& Objects.equals(clienteFinale, other.clienteFinale)
				&& DateUtil.dateCompare((Date) dataFine, (Date) other.dataFine)
				&& DateUtil.dateCompare((Date) dataInizio, (Date) other.dataInizio)
				&& Objects.equals(distacco, other.distacco)
				&& Objects.equals(tariffaGiornaliera, other.tariffaGiornaliera)
				&& Objects.equals(titoloPosizione, other.titoloPosizione);
	}

	public Commessa(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAziendaCliente() {
		return aziendaCliente;
	}

	public void setAziendaCliente(String aziendaCliente) {
		this.aziendaCliente = aziendaCliente;
	}

	public String getClienteFinale() {
		return clienteFinale;
	}

	public void setClienteFinale(String clienteFinale) {
		this.clienteFinale = clienteFinale;
	}

	public String getTitoloPosizione() {
		return titoloPosizione;
	}

	public void setTitoloPosizione(String titoloPosizione) {
		this.titoloPosizione = titoloPosizione;
	}

	public Boolean getDistacco() {
		return distacco;
	}

	public void setDistacco(Boolean distacco) {
		this.distacco = distacco;
	}

	public String getDistaccoAzienda() {
		return distaccoAzienda;
	}

	public void setDistaccoAzienda(String distaccoAzienda) {
		this.distaccoAzienda = distaccoAzienda;
	}

	public Date getDistaccoData() {
		return distaccoData;
	}

	public void setDistaccoData(Date distaccoData) {
		this.distaccoData = distaccoData;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getTariffaGiornaliera() {
		return tariffaGiornaliera;
	}

	public void setTariffaGiornaliera(String tariffaGiornaliera) {
		this.tariffaGiornaliera = tariffaGiornaliera;
	}

	public String getAziendaDiFatturazioneInterna() {
		return aziendaDiFatturazioneInterna;
	}

	public void setAziendaDiFatturazioneInterna(String aziendaDiFatturazioneInterna) {
		this.aziendaDiFatturazioneInterna = aziendaDiFatturazioneInterna;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

}
