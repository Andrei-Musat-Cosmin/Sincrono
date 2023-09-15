package it.sincrono.services.validator;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Contratto;

@Component
public class ContrattoValidator {

	public Boolean validate(Contratto contratto, Boolean isNew) {

		boolean result = true;

		if (contratto != null) {

			if (isNew) {

				if (contratto.getId() == null) {
					if (contratto.getTipoContratto().getId() == null
							|| contratto.getTipoLivelloContratto().getId() == null
							|| contratto.getTipoAzienda().getId() == null || contratto.getTipoCcnl().getId() == null
							|| contratto.getDataAssunzione() == null) {
						result = false;
					}
				} else {

					result = false;
				}
			} else if (contratto.getId() != null) {
				if (contratto.getTipoContratto().getId() == null || contratto.getTipoLivelloContratto().getId() == null
						|| contratto.getTipoAzienda().getId() == null || contratto.getTipoCcnl().getId() == null
						|| contratto.getDataAssunzione() == null) {
					result = false;
				}
			} else {

				result = false;
			}

		} else {

			result = false;
		}

		return result;
	}

	public Boolean validateUpdate(Contratto contratto) {

		boolean result = true;

		if (contratto != null) {

			if (contratto.getTipoContratto().getId() == null || contratto.getTipoLivelloContratto().getId() == null
					|| contratto.getTipoAzienda().getId() == null || contratto.getTipoCcnl().getId() == null
					|| contratto.getDataAssunzione() == null) {
				result = false;

			}
			
		}

			return result;
		

	}

}
