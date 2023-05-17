package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class StoricoCommesse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_anagrafica")
	private Anagrafica idAnagrafica;
	
	
	@ManyToOne
	@JoinColumn(name = "id_commessa")
	private Commessa idCommessa;


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


	public Commessa getIdCommessa() {
		return idCommessa;
	}


	public void setIdCommessa(Commessa idCommessa) {
		this.idCommessa = idCommessa;
	}


	public StoricoCommesse(Integer id, Anagrafica idAnagrafica, Commessa idCommessa) {
		super();
		this.id = id;
		this.idAnagrafica = idAnagrafica;
		this.idCommessa = idCommessa;
	}


	public static StoricoCommesse get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
