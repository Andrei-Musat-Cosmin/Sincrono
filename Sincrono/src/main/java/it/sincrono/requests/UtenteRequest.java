package it.sincrono.requests;

public class UtenteRequest extends GenericRequest {

	Integer id;

	String passwordVecchia;

	String passwordNuova;

	public UtenteRequest(Integer id, String passwordVecchia, String passwordNuova) {
		super();
		this.id = id;
		this.passwordVecchia = passwordVecchia;
		this.passwordNuova = passwordNuova;
	}

	public UtenteRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordVecchia() {
		return passwordVecchia;
	}

	public void setPasswordVecchia(String passwordVecchia) {
		this.passwordVecchia = passwordVecchia;
	}

	public String getPasswordNuova() {
		return passwordNuova;
	}

	public void setPasswordNuova(String passwordNuova) {
		this.passwordNuova = passwordNuova;
	}

}