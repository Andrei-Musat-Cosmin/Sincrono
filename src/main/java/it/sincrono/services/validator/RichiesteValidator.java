package it.sincrono.services.validator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.dto.DuplicazioniRichiestaDto;
import it.sincrono.repositories.dto.RichiestaDto;

@Component
public class RichiesteValidator {
	private static final Logger LOGGER = LogManager.getLogger(RichiesteValidator.class);

	public Boolean validateInsert(RichiestaDto richiestaDto) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

		if (richiestaDto == null || richiestaDto.getId() != null) {
			LOGGER.log(Level.ERROR, "Id della richiestaDto non deve essere valorizzato");
			return false;
		}

		if (richiestaDto.getMese() == null) {
			LOGGER.log(Level.ERROR, "il mese della richiesta deve essere valorizzato");
			return false;
		}

		if (richiestaDto.getAnno() == null) {
			LOGGER.log(Level.ERROR, "l'anno della richiesta deve essere valorizzato");
			return false;
		}

		String codiceFiscale = richiestaDto.getCodiceFiscale();
		if (codiceFiscale == null || codiceFiscale.isEmpty()) {
			LOGGER.log(Level.ERROR, "il codice fiscale della richiesta deve essere valorizzato");
			return false;
		}

		for (DuplicazioniRichiestaDto duplicazioniRichiestaDto : richiestaDto.getList()) {
			if ((duplicazioniRichiestaDto.getFerie() != null || duplicazioniRichiestaDto.getPermessi() != null)
					|| (duplicazioniRichiestaDto.getFerie() == null
							&& duplicazioniRichiestaDto.getPermessi() == null)) {
				if (duplicazioniRichiestaDto.getnGiorno() == null) {
					LOGGER.log(Level.ERROR,
							"nella richiesta se ferie o permessi sono valorizzati, nGiorno deve essere valorizzato");
					return false;
				}

				if (duplicazioniRichiestaDto.getPermessi() != null) {
					String daOra = duplicazioniRichiestaDto.getDaOra();
					String aOra = duplicazioniRichiestaDto.getaOra();
					if (daOra == null || aOra == null || daOra.isEmpty() || aOra.isEmpty()) {
						LOGGER.log(Level.ERROR,
								"nella richiesta se permessi sono valorizzati, daOra e aOra devono essere valorizzati");
						return false;
					} else {

						LocalTime time1 = LocalTime.parse(daOra, formatter);
						LocalTime time2 = LocalTime.parse(aOra, formatter);

						if (time2.isBefore(time1)) {

							LOGGER.log(Level.ERROR, "nella richiesta daOra deve essere minore di aOra");
							return false;

						}

					}

				}
			} else {
				LOGGER.log(Level.ERROR, "nella richiesta deve essere valorizzato o ferie o permessi");
				return false;
			}
		}

		return true;

	}

	public Boolean validateListRichieste(RichiestaDto richiestaDto) {


		if (richiestaDto.getMese() == null) {
			LOGGER.log(Level.ERROR, "il mese della richiesta deve essere valorizzato");
			return false;
		}

		if (richiestaDto.getAnno() == null) {
			LOGGER.log(Level.ERROR, "l'anno della richiesta deve essere valorizzato");
			return false;
		}

		String codiceFiscale = richiestaDto.getCodiceFiscale();
		if (codiceFiscale == null || codiceFiscale.isEmpty()) {
			LOGGER.log(Level.ERROR, "il codice fiscale della richiesta deve essere valorizzato");
			return false;
		}

		return true;

	}
}
