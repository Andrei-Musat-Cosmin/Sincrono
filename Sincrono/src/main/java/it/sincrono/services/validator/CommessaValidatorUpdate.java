package it.sincrono.services.validator;

import it.sincrono.entities.Commessa;

public class CommessaValidatorUpdate {
	
	public Boolean validate(Commessa commessa) {

		
		boolean result=true;
		
		
		 
		
		if (commessa.getCliente()==null && commessa.getCliente().equals("") ||
			commessa.getNominativo()==null && commessa.getNominativo().equals("") ||
				commessa.getDataInizio()==null && commessa.getDataFine()==null) {
				result = false;
			}
		
			
		 
			return result;
				 
		}
			  		
			
}
