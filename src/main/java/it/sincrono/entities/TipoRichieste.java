package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	private String daGiorno;

	@Column(name = "a")
	private String aGiorno;

	@OneToMany
	@JoinColumn(name = "id_richiesta")
	private Richieste richiesta;

	public TipoRichieste() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoRichieste(Integer id, Boolean ferie, Boolean permesso, String daGiorno, String aGiorno,
			Richieste richiesta) {
		super();
		this.id = id;
		this.ferie = ferie;
		this.permesso = permesso;
		this.daGiorno = daGiorno;
		this.aGiorno = aGiorno;
		this.richiesta = richiesta;
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

	public String getDaGiorno() {
		return daGiorno;
	}

	public void setDaGiorno(String daGiorno) {
		this.daGiorno = daGiorno;
	}

	public String getaGiorno() {
		return aGiorno;
	}

	public void setaGiorno(String aGiorno) {
		this.aGiorno = aGiorno;
	}

	public Richieste getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(Richieste richiesta) {
		this.richiesta = richiesta;
	}

}
