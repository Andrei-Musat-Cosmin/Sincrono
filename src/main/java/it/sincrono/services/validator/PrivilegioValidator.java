package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Privilegio;

@Component
public class PrivilegioValidator {

	/**
	 * @param privilegio
	 * @return Boolean
	 */
	public Boolean validate(Privilegio privilegio) {

		boolean result = true;

		if (privilegio != null) {

			if (privilegio.getId() != null) {
				result = false;
			}

			/*
			 * Mandatory fields
			 */
			if (privilegio.getRuolo() == null || privilegio.getRuolo().getId() == null
					|| privilegio.getFunzione() == null || privilegio.getFunzione().getId() == null) {
				result = false;
			}

		} else {
			result = false;
		}

		return result;
	}
}