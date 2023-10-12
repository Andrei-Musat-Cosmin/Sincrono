package it.sincrono.responses;

public class CheckRapportinoInviatoResponse extends GenericResponse{
	
	
	private boolean checkInviato;

	public CheckRapportinoInviatoResponse(boolean checkInviato) {
		super();
		this.checkInviato = checkInviato;
	}

	public CheckRapportinoInviatoResponse() {
		super();
	}

	public boolean isCheckInviato() {
		return checkInviato;
	}

	public void setCheckInviato(boolean checkInviato) {
		this.checkInviato = checkInviato;
	}
	
	
	
	

}
