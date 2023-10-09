package it.sincrono.services.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.dto.RapportinoDto;

@Component
public class RapportinoValidator {
	private static final Logger LOGGER = LogManager.getLogger(RapportinoValidator.class);

	public Boolean validate(RapportinoDto rapportinoDto) {

		if (rapportinoDto != null) {

			if (rapportinoDto.getAnagrafica().getCodiceFiscale() == null ||
					rapportinoDto.getAnagrafica().getCodiceFiscale().equals("")) {
				LOGGER.log(Level.ERROR, "codice fiscale del rapportinoDto non è valorizzato");
				return false;
			}
			if (rapportinoDto.getAnnoRequest() == null) {
				LOGGER.log(Level.ERROR, "anno request del rapportinoDto non è valorizzato");
				return false;
			}

			if (rapportinoDto.getMeseRequest() == null) {
				LOGGER.log(Level.ERROR, "mese request del rapportinoDto non è valorizzato");
				return false;
			}


			return true;

		} else {
			return false;
		}
	}

}
