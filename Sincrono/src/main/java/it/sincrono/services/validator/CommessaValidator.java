package it.sincrono.services.validator;
import org.springframework.stereotype.Component;
import it.sincrono.entities.Commessa;

@Component
public class CommessaValidator {

	public Boolean validate(Commessa commessa, Boolean isNew) {

		boolean result = true;

		if (commessa != null) {

			if (isNew) {

			
			 if (commessa.getId() == null) {
			 

			if (commessa.getCliente()==null && commessa.getCliente().equals("") ||
				commessa.getAzienda()==null && commessa.getAzienda().equals("") ||
				commessa.getDataInizio()==null && commessa.getDataFine()==null) {
				result = false;
			}

			
			}else {
				
				result=false;
			}
			 
			} else {
			  
			  
				  if (commessa.getId() != null) {
				  
					  if (commessa.getCliente()==null && commessa.getCliente().equals("") ||
							  commessa.getAzienda()==null && commessa.getAzienda().equals("") ||
							  commessa.getDataInizio()==null && commessa.getDataFine()==null) {
								result = false;
							}
				  }else {
					  
					  
					  result=false;
				  }

			  }
			

		}else {
			
			result=false;
		}
		
		return result;
		
	}
	
}
