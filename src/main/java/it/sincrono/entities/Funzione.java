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

	@Column(name = "menu_item")
	private Integer menuItem;

	@Column(name = "nome")
	private String nome;

	@Column(name = "percorso")
	private String percorso;

	@Column(name = "ordinamento")
	private Integer ordinamento;

	private transient List<Funzione> funzioni;

	private transient Boolean privilegio;

	public Funzione(Integer id, Funzione funzione, Integer menuItem, String nome, String percorso, Integer ordinamento,
			List<Funzione> funzioni, Boolean privilegio) {
		super();
		this.id = id;
		this.funzione = funzione;
		this.menuItem = menuItem;
		this.nome = nome;
		this.percorso = percorso;
		this.ordinamento = ordinamento;
		this.funzioni = funzioni;
		this.privilegio = privilegio;
	}

	public Funzione() {
		super();
		// TODO Auto-generated constructor stub
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

	public Integer getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(Integer menuItem) {
		this.menuItem = menuItem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPercorso() {
		return percorso;
	}

	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}

	public Integer getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}

	public List<Funzione> getFunzioni() {
		return funzioni;
	}

	public void setFunzioni(List<Funzione> funzioni) {
		this.funzioni = funzioni;
	}

	public Boolean getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Boolean privilegio) {
		this.privilegio = privilegio;
	}

	public Funzione(Integer id) {
		super();
		this.id = id;
	}

}
