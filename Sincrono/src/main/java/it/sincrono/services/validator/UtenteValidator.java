package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Utente;

@Component
public class UtenteValidator {

	public Boolean validate(Utente utente, Boolean isNew) {

		boolean result = true;

		if (utente != null) {

			if (isNew) {

				if (utente.getId() == null) {

					if (utente.getUsername() == null || utente.getUsername().equals("") || utente.getPassword() == null
							|| utente.getPassword().equals("") || utente.getTokenPassword() == null
							|| utente.getTokenPassword().equals("")) {
						result = false;
					}

				} else {

					result = false;
				}
			} else {

				if (utente.getId() != null) {

					if (utente.getUsername() == null || utente.getUsername().equals("") || utente.getPassword() == null
							|| utente.getPassword().equals("") || utente.getTokenPassword() == null
							|| utente.getTokenPassword().equals("")) {
						result = false;
					}
				} else {

					result = false;
				}
			}

		}

		return result;
	}

}
