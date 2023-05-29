package it.sincrono.responses;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Privilegio;

public class PrivilegioResponse extends GenericResponse {
	Privilegio privilegio;

	public Privilegio getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

	public PrivilegioResponse(Esito esito, Privilegio privilegio) {
		super(esito);
		this.privilegio = privilegio;
	}

	public PrivilegioResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrivilegioResponse(Esito esito) {
		super(esito);
		// TODO Auto-generated constructor stub
	}

	
}