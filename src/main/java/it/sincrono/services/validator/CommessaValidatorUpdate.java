package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Commessa;

@Component
public class CommessaValidatorUpdate {

	public Boolean validate(Commessa commessa) {

		boolean result = true;

		if (commessa.getTipoAziendaCliente() == null && commessa.getTipoAziendaCliente().getId() == null
				|| commessa.getClienteFinale() == null || commessa.getDataInizio() == null)
			result = false;

		return result;

	}

}
