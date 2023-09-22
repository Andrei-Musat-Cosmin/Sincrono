package it.sincrono.requests;

public class CambioPasswordRequest extends GenericRequest {

	String token;
	String password;

	public CambioPasswordRequest(String token, String password) {
		super();
		this.token = token;
		this.password = password;
	}

	public CambioPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
