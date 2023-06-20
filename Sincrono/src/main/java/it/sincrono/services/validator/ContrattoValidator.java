package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Contratto;

@Component
public class ContrattoValidator {

	public Boolean validate(Contratto contratto, Boolean isNew) {

		boolean result = true;

		if (contratto != null) {
		
			 if (isNew) {
		
					
				 if (contratto.getId() == null) {
					if (contratto.getTipoContratto().getId()==null||
							contratto.getLivelloContratto().getId() == null || 
							contratto.getTipoAzienda().getId() == null || 
							contratto.getContrattoNazionale().getId() == null 
							) {
						result = false;
					}
				  }else {
					  
					  result=false;
				  }
					
			  } else {
				  
			  
					
				 if (contratto.getId() != null) {
					if (
						contratto.getTipoContratto().getId()==null||
						contratto.getLivelloContratto().getId() == null || 
						contratto.getTipoAzienda().getId() == null || 
						contratto.getContrattoNazionale().getId() == null 
						) {
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
