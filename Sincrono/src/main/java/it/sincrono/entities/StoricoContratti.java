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
	private Anagrafica idAnagrafica;
	
	
	@ManyToOne
	@JoinColumn(name = "id_contratto")
	private Commessa idContratto;

	
	
	

	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}


	public Anagrafica getIdAnagrafica() {
		return idAnagrafica;
	}


	public void setIdAnagrafica(Anagrafica idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}


	public Commessa getIdContratto() {
		return idContratto;
	}


	public void setIdContratto(Commessa idContratto) {
		this.idContratto = idContratto;
	}


	public StoricoContratti(Integer id, Anagrafica idAnagrafica, Commessa idContratto) {
		super();
		this.id = id;
		this.idAnagrafica = idAnagrafica;
		this.idContratto = idContratto;
	}


	public StoricoContratti() {
		super();
	}

	
	
	

}
