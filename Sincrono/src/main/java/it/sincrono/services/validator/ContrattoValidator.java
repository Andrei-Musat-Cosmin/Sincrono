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

					result = checkForIntegrita(contratto, result);

				} else {

					result = false;
				}
			} else if (contratto.getTipoContratto() != null) {

				result = checkForIntegrita(contratto, result);

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
			if (contratto.getTipoContratto() != null) {
				checkForIntegrita(contratto, result);
			}
		}

		return result;

	}

	Boolean checkForIntegrita(Contratto contratto, Boolean result) {
		if (contratto.getTipoContratto() != null) {
			switch (contratto.getTipoContratto().getDescrizione()) {
			case "Stage":
				if (contratto.getLivelloAttuale() != null || contratto.getLivelloFinale() != null
						|| contratto.getRalAnnua() != null || contratto.getSuperminimoRal() != null
						|| contratto.getDiariaMensile() != null || contratto.getDiariaGiornaliera() != null
						|| contratto.getScattiAnzianita() != null || contratto.getTariffaPartitaIva() != null
						|| contratto.getTicket() != null || contratto.getValoreTicket() != null)
					result = false;
				break;
			case "P.Iva":
				if (contratto.getMesiDurata() != null || contratto.getDataFineRapporto() != null
						|| contratto.getLivelloAttuale() != null || contratto.getLivelloFinale() != null
						|| contratto.getSuperminimoMensile() != null || contratto.getRalAnnua() != null
						|| contratto.getSuperminimoRal() != null || contratto.getTutor() != null
						|| contratto.getPfi() != null)
					result = false;
				break;
			case "Determinato":
				if (contratto.getTariffaPartitaIva() != null || contratto.getTutor() != null
						|| contratto.getPfi() != null)
					result = false;

				break;
			case "Indeterminato":
				if (contratto.getMesiDurata() != null || contratto.getDataFineRapporto() != null
						|| contratto.getTariffaPartitaIva() != null || contratto.getTutor() != null
						|| contratto.getPfi() != null)
					result = false;
				break;
			case "Apprendistato":
				if (contratto.getTariffaPartitaIva() != null)
					result = false;
				break;
			default:
				System.out.println("Dato \"TipoContratto\" non riconosciuto");
				result = false;
				break;
			}

		} else {
			result = false;
		}
		if (contratto.getTipoLivelloContratto() == null || contratto.getTipoLivelloContratto().getId() == null) {
			result = false;
		}
		if (contratto.getTipoAzienda() == null || contratto.getTipoAzienda().getId() == null) {
			result = false;
		}
		if (contratto.getTipoCcnl() == null || contratto.getTipoCcnl().getId() == null) {
			result = false;
		}
		if (contratto.getTipoCanaleReclutamento() == null || contratto.getTipoCanaleReclutamento().getId() == null) {
			result = false;
		}
		return result;
	}
}
