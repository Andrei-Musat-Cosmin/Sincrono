package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;

@Component
public class AnagraficaValidator {

	public Boolean validate(Anagrafica anagrafica, Boolean isNew) {

		boolean result = true;

		if (anagrafica != null) {

			if (isNew) {

				if (anagrafica.getId() == null) {

					if (anagrafica.getNome() == null || anagrafica.getNome().equals("")
							|| anagrafica.getCognome() == null || anagrafica.getCognome().equals("")
							|| anagrafica.getCodiceFiscale() == null || anagrafica.getCodiceFiscale().equals("")
							|| anagrafica.getTipoAzienda() == null || anagrafica.getTipoAzienda().getId() == null)

						result = false;

				} else

					result = false;

			} else {

				if (anagrafica.getId() != null) {

					if (anagrafica.getNome() == null || anagrafica.getNome().equals("")
							|| anagrafica.getCognome() == null || anagrafica.getCognome().equals("")
							|| anagrafica.getCodiceFiscale() == null || anagrafica.getCodiceFiscale().equals("")
							|| anagrafica.getTipoAzienda() == null || anagrafica.getTipoAzienda().getId() == null)

						result = false;

				} else

					result = false;
			}
		}
		return result;
	}

}
