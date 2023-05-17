package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;

@Component
public class AnagraficaValidator {

	
	public Boolean validate(Anagrafica anagrafica, Boolean isNew) {

		boolean result = true;

		if (anagrafica != null) {

			//if (isNew) {

				if (anagrafica.getId() != null) {
					result = false;
				}

				if (anagrafica.getNome() == null || anagrafica.getNome().equals("") || 
					anagrafica.getCognome() == null ||anagrafica.getCognome().equals("") ||
					anagrafica.getCodiceFiscale() == null ||anagrafica.getCodiceFiscale().equals("") ||
					anagrafica.getAttivo() == null 
					) {
					result = false;
				}

			/*} else {

				
				if (anagrafica.getId() == null) {
					result = false;
				}

			
				if (anagrafica != null && ruolo.getRuolo().getId() == ruolo.getId()) {
					result = false;
				}

				
				if (anagrafica.getNome() == null || anagrafica.getNome().equals("") || 
					anagrafica.getCognome() == null ||anagrafica.getCognome().equals("") ||
					anagrafica.getCodiceFiscale() == null ||anagrafica.getCodiceFiscale().equals("") ||
					anagrafica.getAttivo() == null ) {
					result = false;
				}
			}
		}*/

		
		}
		
		return result;
	}
	
}
	

