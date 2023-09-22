package it.sincrono.requests;

import it.sincrono.entities.Privilegio;

public class PrivilegioRequest {

	private Privilegio privilegio;

	public Privilegio getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

	public PrivilegioRequest(Privilegio privilegio) {
		super();
		this.privilegio = privilegio;
	}

	public PrivilegioRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	}

