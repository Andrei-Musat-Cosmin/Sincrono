package it.sincrono.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ruoli")
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_padre")
	private Ruolo ruolo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descrizione")
	private String descrizione;

	private transient List<Ruolo> ruoli;
	
	

	public Ruolo(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Ruolo(Integer id, Ruolo ruolo, String nome, String descrizione, List<Ruolo> ruoli) {
		super();
		this.id = id;
		this.ruolo = ruolo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.ruoli = ruoli;
	}

	public Ruolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

}
