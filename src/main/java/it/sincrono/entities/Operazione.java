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
@Table(name = "operazioni")
public class Operazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_funzione")
	private Funzione funzione;

	@Column(name = "percorso")
	private String percorso;

	@Column(name = "metodo")
	private String metodo;

	public Operazione(Integer id, Funzione funzione, String percorso, String metodo) {
		super();
		this.id = id;
		this.funzione = funzione;
		this.percorso = percorso;
		this.metodo = metodo;
	}

	public Operazione() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funzione getFunzione() {
		return funzione;
	}

	public void setFunzione(Funzione funzione) {
		this.funzione = funzione;
	}

	public String getPercorso() {
		return percorso;
	}

	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

}
