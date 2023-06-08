package it.sincrono.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_livelli_contrattuali")
public class LivelloContratto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "ccnl")
	private String ccnl;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "minimi_ret_23")
	private String minimiRet23;

	public LivelloContratto(Integer id, String ccnl, String descrizione, String minimiRet23) {
		super();
		this.id = id;
		this.ccnl = ccnl;
		this.descrizione = descrizione;
		this.minimiRet23 = minimiRet23;
	}

	public LivelloContratto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCcnl() {
		return ccnl;
	}

	public void setCcnl(String ccnl) {
		this.ccnl = ccnl;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMinimiRet23() {
		return minimiRet23;
	}

	public void setMinimiRet23(String minimiRet23) {
		this.minimiRet23 = minimiRet23;
	}

}
