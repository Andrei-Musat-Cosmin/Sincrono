package it.sincrono.services.validator;

import it.sincrono.entities.StoricoContratti;

public class StoricoContrattiValidator {
	
	public Boolean validate(StoricoContratti storicoContratti, Boolean isNew) {

	
		boolean result = true;
	
		if (storicoContratti != null) {
	
			//if (isNew) {
	
				if (storicoContratti.getId() != null && storicoContratti.getIdAnagrafica()== null && storicoContratti.getIdContratto()!=null) {
					result = false;
				}

		}
				
				
		return result;
	
	}
	
}
