package it.sincrono.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Apprendistato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String durata_mesi;

	@Column
	private String livello_inquadramento;

	@Column
	private String retribuzione;

	@Column
	private String periodo;

	@Column
	private Date data_inizio;

	@Column
	private Date data_fine;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDurata_mesi() {
		return durata_mesi;
	}

	public void setDurata_mesi(String durata_mesi) {
		this.durata_mesi = durata_mesi;
	}

	public String getLivello_inquadramento() {
		return livello_inquadramento;
	}

	public void setLivello_inquadramento(String livello_inquadramento) {
		this.livello_inquadramento = livello_inquadramento;
	}

	public String getRetribuzione() {
		return retribuzione;
	}

	public void setRetribuzione(String retribuzione) {
		this.retribuzione = retribuzione;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Date getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(Date data_inizio) {
		this.data_inizio = data_inizio;
	}

	public Date getData_fine() {
		return data_fine;
	}

	public void setData_fine(Date data_fine) {
		this.data_fine = data_fine;
	}

	public Apprendistato() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apprendistato(Integer id, String durata_mesi, String livello_inquadramento, String retribuzione,
			String periodo, Date data_inizio, Date data_fine) {
		super();
		this.id = id;
		this.durata_mesi = durata_mesi;
		this.livello_inquadramento = livello_inquadramento;
		this.retribuzione = retribuzione;
		this.periodo = periodo;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
	}

}
