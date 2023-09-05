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

	@Column(name = "cliente")
	private String cliente;

	@Column(name = "cliente_finale")
	private String clienteFinale;

	@Column(name = "titolo_posizione")
	private String titoloPosizione;

	@Column(name = "distacco")
	private String distacco;

	@Column(name = "data_inizio")
	private Date dataInizio;

	@Column(name = "data_fine")
	private Date dataFine;

	@Column(name = "costo_mese")
	private String costoMese;

	@Column(name = "tariffa_giornaliera")
	private String tariffaGiornaliera;

	@Column(name = "nominativo")
	private String nominativo;

	@Column(name = "azienda")
	private String azienda;

	@Column(name = "azienda_di_fatturazione_interna")
	private String aziendaDiFatturazioneInterna;

	@Column(name = "stato")
	private Boolean stato;

	@Column(name = "attesaLavori")
	private String attesaLavori;

	public Commessa(Integer id, String cliente, String clienteFinale, String titoloPosizione, String distacco,
			Date dataInizio, Date dataFine, String costoMese, String tariffaGiornaliera, String nominativo,
			String azienda, String aziendaDiFatturazioneInterna, Boolean stato, String attesaLavori) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.clienteFinale = clienteFinale;
		this.titoloPosizione = titoloPosizione;
		this.distacco = distacco;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.costoMese = costoMese;
		this.tariffaGiornaliera = tariffaGiornaliera;
		this.nominativo = nominativo;
		this.azienda = azienda;
		this.aziendaDiFatturazioneInterna = aziendaDiFatturazioneInterna;
		this.stato = stato;
		this.attesaLavori = attesaLavori;

	}

	public Commessa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(attesaLavori, azienda, aziendaDiFatturazioneInterna, cliente, clienteFinale, costoMese,
				dataFine, dataInizio, distacco, id, nominativo, stato, tariffaGiornaliera, titoloPosizione);
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
		return Objects.equals(attesaLavori, other.attesaLavori) && Objects.equals(azienda, other.azienda)
				&& Objects.equals(aziendaDiFatturazioneInterna, other.aziendaDiFatturazioneInterna)
				&& Objects.equals(cliente, other.cliente) && Objects.equals(clienteFinale, other.clienteFinale)
				&& Objects.equals(costoMese, other.costoMese) && DateUtil.dateCompare((Date)dataFine, (Date)other.dataFine)
				&& DateUtil.dateCompare((Date)dataInizio, (Date)other.dataInizio) && Objects.equals(distacco, other.distacco)
				&&  Objects.equals(nominativo, other.nominativo)
				&&  Objects.equals(tariffaGiornaliera, other.tariffaGiornaliera)
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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

	public String getDistacco() {
		return distacco;
	}

	public void setDistacco(String distacco) {
		this.distacco = distacco;
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

	public String getCostoMese() {
		return costoMese;
	}

	public void setCostoMese(String costoMese) {
		this.costoMese = costoMese;
	}

	public String getTariffaGiornaliera() {
		return tariffaGiornaliera;
	}

	public void setTariffaGiornaliera(String tariffaGiornaliera) {
		this.tariffaGiornaliera = tariffaGiornaliera;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public String getAziendaDiFatturazioneInterna() {
		return aziendaDiFatturazioneInterna;
	}

	public void setAziendaDiFatturazioneInterna(String aziendaDiFatturazioneInterna) {
		this.aziendaDiFatturazioneInterna = aziendaDiFatturazioneInterna;
	}

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public String getAttesaLavori() {
		return attesaLavori;
	}

	public void setAttesaLavori(String attesaLavori) {
		this.attesaLavori = attesaLavori;
	}

}
