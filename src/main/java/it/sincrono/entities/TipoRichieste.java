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
@Table(name = "tipo_richieste")
public class TipoRichieste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "ferie")
	private Boolean ferie;

	@Column(name = "permesso")
	private Boolean permesso;

	@Column(name = "da")
	private String daOra;
	
	@Column(name = "a")
	private String aOra;

	@ManyToOne
	@JoinColumn(name = "id_richiesta")
	private Richieste richiesta;
	
	@Column(name = "n_giorno")
	private Integer nGiorno;

	public TipoRichieste() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TipoRichieste(Integer id, Boolean ferie, Boolean permesso, String daOra, String aOra, Richieste richiesta,
			Integer nGiorno) {
		super();
		this.id = id;
		this.ferie = ferie;
		this.permesso = permesso;
		this.daOra = daOra;
		this.aOra = aOra;
		this.richiesta = richiesta;
		this.nGiorno = nGiorno;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getFerie() {
		return ferie;
	}

	public void setFerie(Boolean ferie) {
		this.ferie = ferie;
	}

	public Boolean getPermesso() {
		return permesso;
	}

	public void setPermesso(Boolean permesso) {
		this.permesso = permesso;
	}



	public String getDaOra() {
		return daOra;
	}



	public void setDaOra(String daOra) {
		this.daOra = daOra;
	}



	public String getaOra() {
		return aOra;
	}



	public void setaOra(String aOra) {
		this.aOra = aOra;
	}



	public Richieste getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(Richieste richiesta) {
		this.richiesta = richiesta;
	}

	public Integer getnGiorno() {
		return nGiorno;
	}

	public void setnGiorno(Integer nGiorno) {
		this.nGiorno = nGiorno;
	}

	
	

}
