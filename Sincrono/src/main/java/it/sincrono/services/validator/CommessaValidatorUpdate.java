package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Commessa;

@Component
public class CommessaValidatorUpdate {

	public Boolean validate(Commessa commessa) {

		boolean result = true;

		if (commessa.getAziendaCliente() == null && commessa.getAziendaCliente().equals("") ||
				commessa.getDataInizio() == null && commessa.getDataFine() == null) {
			result = false;
		}

		return result;

	}

}
