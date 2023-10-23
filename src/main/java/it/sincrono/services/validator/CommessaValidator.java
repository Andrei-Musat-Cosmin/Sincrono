package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Commessa;

@Component
public class CommessaValidator {

	public Boolean validate(Commessa commessa, Boolean isNew) {

		if (commessa != null) {

			if (isNew) {

				if (commessa.getId() == null) {

					if (commessa.getTipoAziendaCliente() == null && commessa.getTipoAziendaCliente().getId() == null
							|| commessa.getClienteFinale() == null || commessa.getDataInizio() == null)
						return false;

				} else {

					return false;
				}

			} else {

				if (commessa.getId() != null) {

					if (commessa.getTipoAziendaCliente() == null && commessa.getTipoAziendaCliente().getId() == null
							|| commessa.getClienteFinale() == null || commessa.getDataInizio() == null)
						return false;

				} else {

					return false;
				}

			}

		} else {

			return false;
		}

		return true;

	}

}
