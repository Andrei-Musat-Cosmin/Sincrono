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
					if (contratto.getAttivo() == null ||
							contratto.getTipoContratto().getDescrizione() == null||
							contratto.getTipoContratto().getDescrizione().equals("") || 
							 contratto.getLivelloContratto().getDescrizione() == null || 
							 contratto.getLivelloContratto().getDescrizione().equals("") ||
							 contratto.getTipoAzienda().getDescrizione() == null || 
							 contratto.getTipoAzienda().getDescrizione().equals("") ||
							 contratto.getContrattoNazionale().getDescrizione() == null || 
							 contratto.getContrattoNazionale().getDescrizione().equals("")) {
						result = false;
					}
				  }
					
			  } else {
				  
			  
					
				 if (contratto.getId() != null) {
					if (contratto.getAttivo() == null ||
							contratto.getTipoContratto().getDescrizione() == null||
							contratto.getTipoContratto().getDescrizione().equals("") || 
							 contratto.getLivelloContratto().getDescrizione() == null || 
							 contratto.getLivelloContratto().getDescrizione().equals("") ||
							 contratto.getTipoAzienda().getDescrizione() == null || 
							 contratto.getTipoAzienda().getDescrizione().equals("") ||
							 contratto.getContrattoNazionale().getDescrizione() == null || 
							 contratto.getContrattoNazionale().getDescrizione().equals("")) {
						result = false;
					}
				  }

				
			  }

		}
		
		 return result;
	}

}
