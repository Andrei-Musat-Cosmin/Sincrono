package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "storico_contratti")
public class StoricoContratti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_anagrafica")
	private Anagrafica anagrafica;

	@ManyToOne
	@JoinColumn(name = "id_contratto")
	private Contratto contratto;

	@Column(name = "utente_aggiornamento")
	private String utenteAggiornamento;

	@Column(name = "data_aggiornamento")
	private String dataAggiornamento;

	public StoricoContratti(Integer id, Anagrafica anagrafica, Contratto contratto, String utenteAggiornamento,
			String dataAggiornamento) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
		this.contratto = contratto;
		this.utenteAggiornamento = utenteAggiornamento;
		this.dataAggiornamento = dataAggiornamento;
	}

	public StoricoContratti(Integer id, Anagrafica anagrafica, Contratto contratto) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
		this.contratto = contratto;
	}

	public StoricoContratti(Anagrafica anagrafica, Contratto contratto) {
		super();
		this.anagrafica = anagrafica;
		this.contratto = contratto;
	}

	public StoricoContratti() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public String getUtenteAggiornamento() {
		return utenteAggiornamento;
	}

	public void setUtenteAggiornamento(String utenteAggiornamento) {
		this.utenteAggiornamento = utenteAggiornamento;
	}

	public String getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

}
