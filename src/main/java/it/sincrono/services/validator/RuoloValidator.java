package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Ruolo;

@Component
public class RuoloValidator {

	public Boolean validate(Ruolo ruolo, Boolean isNew) {

		boolean result = true;

		if (ruolo != null) {

			if (isNew) {

				if (ruolo.getId() != null) {
					result = false;
				}

				/*
				 * if (ruolo.getNome() == null || ruolo.getNome().equals("") ||
				 * ruolo.getDescrizione() == null || ruolo.getDescrizione().equals("")) { result
				 * = false;
				 */
			}

			else {

				if (ruolo.getId() == null) {
					result = false;
				}

				/*
				 * if (ruolo.getRuolo() != null && ruolo.getRuolo().getId() == ruolo.getId()) {
				 * result = false; }
				 * 
				 * if (ruolo.getNome() == null || ruolo.getNome().equals("") ||
				 * ruolo.getDescrizione() == null || ruolo.getDescrizione().equals("")) { result
				 * = false; }
				 */
			}

		} else {

			return false;
		}

		return result;
	}

}
