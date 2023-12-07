package it.sincrono.services.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Contratto;

@Component
public class ContrattoValidator {
	private static final Logger LOGGER = LogManager.getLogger(ContrattoValidator.class);

	public Boolean validate(Contratto contratto, Boolean isNew) {

		if (contratto != null) {

			if (isNew) {

				if (contratto.getId() == null) {

					return checkForIntegrita(contratto);

				} else {
					LOGGER.log(Level.ERROR, "ID del contratto non deve essere valorizzato");
					return false;
				}
			} else if (contratto.getId() != null) {

				return checkForIntegrita(contratto);

			} else {
				LOGGER.log(Level.ERROR, "ID del contratto deve essere valorizzato");
				return false;
			}

		} else {

			return false;
		}

	}

	public Boolean validateUpdate(Contratto contratto) {

		if (contratto != null) {

			return checkForIntegrita(contratto);
		} else {
			return false;
		}

	}

	Boolean checkForIntegrita(Contratto contratto) {
		if (contratto.getTipoContratto() != null) {
			switch (contratto.getTipoContratto().getId()) {
			case 1:
				if (contratto.getLivelloAttuale() != null || contratto.getLivelloFinale() != null
						|| contratto.getRalAnnua() != null || contratto.getSuperminimoRal() != null
						|| contratto.getDiariaMensile() != null || contratto.getDiariaGiornaliera() != null
						|| contratto.getScattiAnzianita() != null || contratto.getTariffaPartitaIva() != null
						|| contratto.getTicket() != null || contratto.getValoreTicket() != null) {
					LOGGER.log(Level.ERROR, "I valori inseriti non rispettano la tipologia del contratto: \""
							+ contratto.getTipoContratto().getDescrizione() + "\".");
					return false;
				}
				break;
			case 2:
				if (contratto.getMesiDurata() != null || contratto.getDataFineRapporto() != null
						|| contratto.getLivelloAttuale() != null || contratto.getLivelloFinale() != null
						|| contratto.getSuperminimoMensile() != null || contratto.getRalAnnua() != null
						|| contratto.getSuperminimoRal() != null || contratto.getTutor() != null
						|| contratto.getPfi() != null) {
					LOGGER.log(Level.ERROR, "I valori inseriti non rispettano la tipologia del contratto: \""
							+ contratto.getTipoContratto().getDescrizione() + "\".");
					return false;
				}
				break;
			case 3:
				if (contratto.getTariffaPartitaIva() != null || contratto.getTutor() != null
						|| contratto.getPfi() != null) {
					LOGGER.log(Level.ERROR, "I valori inseriti non rispettano la tipologia del contratto: \""
							+ contratto.getTipoContratto().getDescrizione() + "\".");
					return false;
				}

				break;
			case 4:
				if (contratto.getMesiDurata() != null || contratto.getDataFineRapporto() != null
						|| contratto.getTariffaPartitaIva() != null || contratto.getTutor() != null
						|| contratto.getPfi() != null) {
					LOGGER.log(Level.ERROR, "I valori inseriti non rispettano la tipologia del contratto: \""
							+ contratto.getTipoContratto().getDescrizione() + "\".");
					return false;
				}
				break;
			case 5:
				if (contratto.getTariffaPartitaIva() != null) {
					LOGGER.log(Level.ERROR, "I valori inseriti non rispettano la tipologia del contratto: \""
							+ contratto.getTipoContratto().getDescrizione() + "\".");
					return false;
				}
				break;
			default:
				LOGGER.log(Level.ERROR, "Il contratto inserito non e' previsto: \""
						+ contratto.getTipoContratto().getDescrizione() + "\".");
				return false;
			}

		} else {
			LOGGER.log(Level.ERROR, "Dato \"Contratto\" non è stato inserito");
			return false;
		}
		if (contratto.getTipoLivelloContratto() == null || contratto.getTipoLivelloContratto().getId() == null) {
			LOGGER.log(Level.ERROR, "Dato \"Livello Contratto\" non è stato inserito");
			return false;
		}
		if (contratto.getTipoCcnl() == null || contratto.getTipoCcnl().getId() == null) {
			LOGGER.log(Level.ERROR, "Dato \"CCNL\" non è stato inserito");
			return false;
		}
		
		return true;
	}
}
