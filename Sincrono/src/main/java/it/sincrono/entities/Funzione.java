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
@Table(name = "funzioni")
public class Funzione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_padre")
	private Funzione funzione;

	@Column(name = "menuitem")
	private Boolean menuItem;

	@Column(name = "nome")
	private String nome;

	@Column(name = "percorso")
	private String percorso;

	@Column(name = "immagine")
	private String immagine;

	@Column(name = "ordinamento")
	private Integer ordinamento;

	private transient List<Funzione> funzioni;

	private transient Boolean privilegio;
	
	public Funzione(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Funzione(Integer id) {
		super();
		this.id = id;
	}

	public Funzione(Integer id, String nome, String percorso) {
		super();
		this.id = id;
		this.nome = nome;
		this.percorso = percorso;
	}
	
	public Funzione(Integer id, Funzione funzione, String nome, String percorso) {
		super();
		this.id = id;
		this.funzione = funzione;
		this.nome = nome;
		this.percorso = percorso;
	}
}
