package it.sincrono.services.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.dto.DuplicazioniGiornoDto;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoRequest;
import it.sincrono.services.utils.MapperCustom;

@Component
public class RapportinoValidator {
	private static final Logger LOGGER = LogManager.getLogger(RapportinoValidator.class);

	@Autowired
	MapperCustom mapperCustom;

	@Autowired
	AnagraficaRepository anagraficaRepository;

	public String validateFieldsForPath(RapportinoDto rapportinoDto) {
		String msg = null;
		if (rapportinoDto != null) {

			if (rapportinoDto.getAnagrafica().getCodiceFiscale() == null
					|| rapportinoDto.getAnagrafica().getCodiceFiscale().equals("")) {
				msg = " Codice fiscale del rapportinoDto non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			Anagrafica anagrafica = anagraficaRepository
					.findByCodiceFiscale(rapportinoDto.getAnagrafica().getCodiceFiscale());

			if (anagrafica == null) {
				msg = " Anagrafica non esistente";
				LOGGER.log(Level.ERROR, msg);

				return msg;
			}

			if (rapportinoDto.getAnnoRequest() == null) {
				msg = " Anno del rapportino non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (rapportinoDto.getMeseRequest() == null) {
				msg = " Mese del rapportino non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			return msg;

		} else {
			msg = " Rapportino non è valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
	}

	public String validateGiornoDto(RapportinoDto rapportinoDto) {
		Contratto contratto = null;
		String msg = null;
		if ((msg = validateFieldsForPath(rapportinoDto)) == null) {
			contratto = mapperCustom.toContratto(
					anagraficaRepository.findByCodiceFiscale(rapportinoDto.getAnagrafica().getCodiceFiscale()).getId());
		} else {
			return msg;
		}

		for (GiornoDto giornoDto : rapportinoDto.getMese().getGiorni()) {

			if (giornoDto.getFerie() == null && giornoDto.getMalattie() == null && giornoDto.getPermessi() == null) {
				for (DuplicazioniGiornoDto giornoDuplicato : giornoDto.getDuplicazioniGiornoDto()) {
					if (giornoDuplicato.getGiorno() != null) {
						if (giornoDuplicato.getCliente() == null) {
							msg = " Il giorno: " + giornoDuplicato.getGiorno() + " non contiente il cliente";
							LOGGER.log(Level.ERROR, msg);
							return msg;
						}
						if (giornoDuplicato.getOreOrdinarie() == null) {
							msg = " Il giorno: " + giornoDuplicato.getGiorno() + " non contiente le ore lavorate";
							LOGGER.log(Level.ERROR, msg);
							return msg;
						}
						if (giornoDto.getNote() == null || giornoDto.getNote().equals("")) {
							msg = " Il giorno: " + giornoDuplicato.getGiorno() + " non contiente le note";
							LOGGER.log(Level.ERROR, msg);
							return msg;
						}
					} else {
						if ((msg = checkPerGliAltriDati(giornoDuplicato, giornoDto)) != null)
							return msg;
					}
				}
			} else if ((giornoDto.getFerie() != null && giornoDto.getMalattie() == null
					&& giornoDto.getPermessi() == null)
					|| (giornoDto.getMalattie() != null && giornoDto.getFerie() == null
							&& giornoDto.getPermessi() == null)
					|| (giornoDto.getPermessi() != null && giornoDto.getFerie() == null
							&& giornoDto.getMalattie() == null)) {

				for (DuplicazioniGiornoDto giornoDuplicato : giornoDto.getDuplicazioniGiornoDto()) {
					if (giornoDuplicato.getGiorno() != null) {
						if (giornoDto.getPermessi() != null) {
							if (giornoDuplicato.getCliente() == null) {
								msg = " Il giorno " + giornoDuplicato.getGiorno() + " non conitente il cliente";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getOreOrdinarie() == null) {

								msg = " Il giorno " + giornoDuplicato.getGiorno() + " non contiente le ore lavorate";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
						} else {
							if (giornoDuplicato.getOreOrdinarie() != null || giornoDuplicato.getFascia1() != null
									|| giornoDuplicato.getFascia2() != null || giornoDuplicato.getFascia3() != null) {
								msg = " Sono state valorizzate delle ore lavorate nel giorno "
										+ giornoDuplicato.getGiorno() + " segnato con "
										+ (giornoDto.getFerie() != null ? "ferie" : "malattia");
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
						}
					} else {
						if ((msg = checkPerGliAltriDati(giornoDuplicato, giornoDto)) != null)
							return msg;
					}
				}
			} else {
				msg = " Un giorno è stato segnato con piu di uno fra i seguenti: ferie, malattie e permessi";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (contratto != null && contratto.getTipoContratto() != null) {

				if (contratto.getTipoContratto().getId() == 1 || contratto.getTipoContratto().getId() == 2) {
					if (giornoDto.getFerie() != null || giornoDto.getPermessi() != null
							|| giornoDto.getMalattie() != null) {
						msg = " Non sono previsti ferie malattie o permessi per il contratto: "
								+ contratto.getTipoContratto().getDescrizione();
						LOGGER.log(Level.ERROR, msg);
						return msg;
					}
				}
			}
		}
		return msg;
	}

	public String validateNote(RapportinoDto rapportinoDto) {
		String msg = null;

		if ((msg = validateFieldsForPath(rapportinoDto)) != null) {
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (rapportinoDto.getNote() == null || rapportinoDto.getNote().equals("")) {
			msg = " Le note non sono state inserte correttamente";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		return msg;

	}

	public String validateRapportiniInviati(RapportinoInviato rapportinoInviato) {
		String msg = null;
		if (rapportinoInviato.getId() == null) {

			if (rapportinoInviato.getNome() == null || rapportinoInviato.getNome().equals("")) {
				msg = " Le nome non è stato inserito corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getCognome() == null || rapportinoInviato.getCognome().equals("")) {
				msg = " Il cognome non è stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getCodiceFiscale() == null || rapportinoInviato.getCodiceFiscale().equals("")) {
				msg = " Il codice fiscale non è stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getMese() == null || rapportinoInviato.getAnno() == null) {
				msg = " Il mese non è stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;

			}

		} else {
			msg = " Non è stato possibile effettuare l'inserimento, il campo \"id\" è valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		return msg;

	}

	public String validateFreeze(RapportinoInviato rapportinoInviato) {
		String msg = null;
		if (rapportinoInviato.getId() != null) {

			if (rapportinoInviato.getCheckFreeze() == null) {
				msg = " Non è stato valorizzato il valore \"checkFreeze\"";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

		} else {
			msg = " Non è stato possibile effettuare l'inserimento, il campo \"id\" è valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		return msg;

	}

	public String validateFieldsForPath(RapportinoRequest rapportinoRequest) {
		String msg = null;
		if (rapportinoRequest != null) {

			if (rapportinoRequest.getCodiceFiscale() == null || rapportinoRequest.getCodiceFiscale().equals("")) {
				msg = " Codice fiscale del rapportinoRequest non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(rapportinoRequest.getCodiceFiscale());

			if (anagrafica == null) {
				msg = " Non esiste anagrafica con quel codice fiscale";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (rapportinoRequest.getAnno() == null) {
				msg = " Anno del rapportino non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (rapportinoRequest.getMese() == null) {
				msg = " Mese del rapportino non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

		} else {
			msg = " Rapportino non è valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		return msg;
	}

	private String checkPerGliAltriDati(DuplicazioniGiornoDto giornoDuplicato, GiornoDto giornoDto) {
		String msg = null;
		if (giornoDuplicato.getCliente() != null) {
			msg = " E' stato segnato il cliente in un giorno senza numero";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDuplicato.getOreOrdinarie() != null) {
			msg = " Sono state segnate delle ore in un giorno senza numero";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDuplicato.getFascia1() != null) {
			msg = " Sono state segnate delle ore di straordinario fascia 18-20 in un giorno senza numero";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDuplicato.getFascia2() != null) {
			msg = " Sono state segnate delle ore di straordinario fascia 20-22 in un giorno senza numero";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDuplicato.getFascia3() != null) {
			msg = " Sono state segnate delle ore di straordinario fascia 22-09 in un giorno senza numero";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDto.getFerie() != null) {
			msg = " Un giorno senza numero è stato segnato come feriale";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDto.getMalattie() != null) {
			msg = " Un giorno senza numero è stato segnato come malattia";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDto.getPermessi() != null) {
			msg = " Un giorno senza numero è stato segnato con delle ore di permesso";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		if (giornoDto.getNote() != null) {
			msg = " Un giorno senza numero è stato segnato con delle note";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		return msg;
	}
}
