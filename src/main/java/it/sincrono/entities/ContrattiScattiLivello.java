package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contratti_scatti_livello")
public class ContrattiScattiLivello {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "id_contratto")
	private Integer idContratto;

	public ContrattiScattiLivello(Integer id, Integer idContratto) {
		super();
		this.id = id;
		this.idContratto = idContratto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdContratto() {
		return idContratto;
	}

	public void setIdContratto(Integer idContratto) {
		this.idContratto = idContratto;
	}

}
