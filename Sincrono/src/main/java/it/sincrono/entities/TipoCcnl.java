package it.sincrono.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_ccnl")
public class TipoCcnl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "numero_mensilita")
	private Integer numeroMensilita;

	public TipoCcnl(Integer id, String descrizione, Integer numeroMensilita) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.numeroMensilita = numeroMensilita;

	}

	public TipoCcnl() {
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
		TipoCcnl other = (TipoCcnl) obj;
		return Objects.equals(descrizione, other.descrizione) && Objects.equals(id, other.id)
				&& Objects.equals(numeroMensilita, other.numeroMensilita);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getNumeroMensilita() {
		return numeroMensilita;
	}

	public void setNumeroMensilita(Integer numeroMensilita) {
		this.numeroMensilita = numeroMensilita;
	}

}
