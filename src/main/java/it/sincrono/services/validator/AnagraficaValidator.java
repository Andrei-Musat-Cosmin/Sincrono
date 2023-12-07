package it.sincrono.services.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;

@Component
public class AnagraficaValidator {
	private static final Logger LOGGER = LogManager.getLogger(AnagraficaValidator.class);

	public Boolean validate(Anagrafica anagrafica, Boolean isNew) {

		if (anagrafica != null) {

			if (isNew) {

				if (anagrafica.getId() == null) {

					if (anagrafica.getNome() == null || anagrafica.getNome().equals("")) {
						LOGGER.log(Level.ERROR, "Nome dell'anagrafica non è valorizzato");
						return false;
					}
					if (anagrafica.getCognome() == null || anagrafica.getCognome().equals("")) {
						LOGGER.log(Level.ERROR, "Cognome dell'anagrafica non è valorizzato");
						return false;
					}

					if (anagrafica.getCodiceFiscale() == null || anagrafica.getCodiceFiscale().equals("")) {
						LOGGER.log(Level.ERROR, "Codice Fiscale dell'anagrafica non è valorizzato");
						return false;
					}

					if (anagrafica.getTipoAzienda() == null || anagrafica.getTipoAzienda().getId() == null) {
						LOGGER.log(Level.ERROR, "Nome Azienda dell'anagrafica non è valorizzato");
						return false;
					}
					
					if (anagrafica.getTipoCanaleReclutamento() == null
							|| anagrafica.getTipoCanaleReclutamento().getId() == null) {
						LOGGER.log(Level.ERROR, "Dato \"Canale Reclutamento\" non è stato inserito");
						return false;
					}

				} else {
					LOGGER.log(Level.ERROR, "Id dell'anagrafica non deve essere valorizzato");
					return false;
				}

			} else {

				if (anagrafica.getId() != null) {
					if (anagrafica.getNome() == null || anagrafica.getNome().equals("")) {
						LOGGER.log(Level.ERROR, "Nome dell'anagrafica non è valorizzato");
						return false;
					}
					if (anagrafica.getCognome() == null || anagrafica.getCognome().equals("")) {
						LOGGER.log(Level.ERROR, "Cognome dell'anagrafica non è valorizzato");
						return false;
					}

					if (anagrafica.getCodiceFiscale() == null || anagrafica.getCodiceFiscale().equals("")) {
						LOGGER.log(Level.ERROR, "Codice Fiscale dell'anagrafica non è valorizzato");
						return false;
					}

					if (anagrafica.getTipoAzienda() == null || anagrafica.getTipoAzienda().getId() == null) {
						LOGGER.log(Level.ERROR, "Nome Azienda dell'anagrafica non è valorizzato");
						return false;
					}
					if (anagrafica.getTipoCanaleReclutamento() == null
							|| anagrafica.getTipoCanaleReclutamento().getId() == null) {
						LOGGER.log(Level.ERROR, "Dato \"Canale Reclutamento\" non è stato inserito");
						return false;
					}

				} else {
					LOGGER.log(Level.ERROR, "Id dell'anagrafica deve essere valorizzato");
					return false;
				}

			}
			return true;

		} else {
			return false;
		}
	}

}
