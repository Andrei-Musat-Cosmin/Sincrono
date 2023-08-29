package it.sincrono.requests;

public class ModificaPasswordRequest extends GenericRequest {
	private Integer id;

	private String passwordVecchia;

	private String passwordNuova;

	public ModificaPasswordRequest(Integer id, String passwordVecchia, String passwordNuova) {
		super();
		this.id = id;
		this.passwordVecchia = passwordVecchia;
		this.passwordNuova = passwordNuova;
	}

	public ModificaPasswordRequest() {
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