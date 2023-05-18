package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.StoricoContratti;

@Component
public class StoricoContrattiValidator {

	public Boolean validate(StoricoContratti storicoContratti, Boolean isNew) {

		boolean result = true;

		if (storicoContratti != null) {

			// if (isNew) {

			if (storicoContratti.getId() != null && storicoContratti.getAnagrafica() == null
					&& storicoContratti.getContratto() != null) {
				result = false;
			}

		}

		return result;

	}

}
