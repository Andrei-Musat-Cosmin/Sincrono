package it.sincrono.services.validator;

import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Contratto;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.services.utils.MapperCustom;

@Component
public class RapportinoValidator {
	private static final Logger LOGGER = LogManager.getLogger(RapportinoValidator.class);

	@Autowired
	MapperCustom mapperCustom;

	@Autowired
	AnagraficaRepository anagraficaRepository;

	public Boolean getValidate(RapportinoDto rapportinoDto) {

		if (rapportinoDto != null) {

			if (rapportinoDto.getAnagrafica().getCodiceFiscale() == null
					|| rapportinoDto.getAnagrafica().getCodiceFiscale().equals("")) {
				LOGGER.log(Level.ERROR, "codice fiscale del rapportinoDto non è valorizzato");
				return false;
			}

			Anagrafica anagrafica = anagraficaRepository
					.findByCodiceFiscale(rapportinoDto.getAnagrafica().getCodiceFiscale());

			if (anagrafica == null) {

				LOGGER.log(Level.ERROR, "anagrafica non esistente");

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

	public Boolean updateValidate(RapportinoDto rapportinoDto, Anagrafica anagrafica) {

		Contratto contratto = null;

		if (getValidate(rapportinoDto)) {

			contratto = mapperCustom.toContratto(anagrafica.getId());

		}else {
			
			
			return false;
			
		}

		for (GiornoDto giornoDto : rapportinoDto.getMese().getGiorni()) {

			if (!((giornoDto.getGiorno() == null &&

					giornoDto.getCliente() == null &&

					giornoDto.getOreOrdinarie() == null) ||

					(giornoDto.getGiorno() != null &&

							(giornoDto.getCliente() != null && !giornoDto.getCliente().isEmpty()) &&

							(giornoDto.getOreOrdinarie() != null && !giornoDto.getOreOrdinarie().isEmpty())))) {

				return false;

			}

			if (contratto != null && contratto.getTipoContratto() != null) {

				if (contratto.getTipoContratto().getId() == 1 || contratto.getTipoContratto().getId() == 2) {

					// mettere altri campi che devono essere null
				}

			}

		}

		return true;

	}

}
