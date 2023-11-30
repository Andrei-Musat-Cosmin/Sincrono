package it.sincrono.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_livelli_contrattuali")
public class TipoLivelloContratto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "ccnl")
	private String ccnl;

	@Column(name = "livello")
	private String livello;

	@Column(name = "minimi_ret_23")
	private Double minimiRet23;

	public TipoLivelloContratto(Integer id, String ccnl, String livello, Double minimiRet23) {
		super();
		this.id = id;
		this.ccnl = ccnl;
		this.livello = livello;
		this.minimiRet23 = minimiRet23;
	}

	public TipoLivelloContratto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoLivelloContratto other = (TipoLivelloContratto) obj;
		return Objects.equals(id, other.id);
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

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public Double getMinimiRet23() {
		return minimiRet23;
	}

	public void setMinimiRet23(Double minimiRet23) {
		this.minimiRet23 = minimiRet23;
	}

}
