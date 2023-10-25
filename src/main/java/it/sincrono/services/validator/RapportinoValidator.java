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
				msg = "codice fiscale del rapportinoDto non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			Anagrafica anagrafica = anagraficaRepository
					.findByCodiceFiscale(rapportinoDto.getAnagrafica().getCodiceFiscale());

			if (anagrafica == null) {
				msg = "anagrafica non esistente";
				LOGGER.log(Level.ERROR, msg);

				return msg;
			}

			if (rapportinoDto.getAnnoRequest() == null) {
				msg = "anno request del rapportinoDto non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (rapportinoDto.getMeseRequest() == null) {
				msg = "mese request del rapportinoDto non è valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			return msg;

		} else {
			msg = "rapportino non è valorizzato";
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

			if (giornoDto.getDuplicazioniGiornoDto() != null) {
				if (giornoDto.getFerie() == null && giornoDto.getMalattie() == null
						&& giornoDto.getPermessi() == null) {
					for (DuplicazioniGiornoDto giornoDuplicato : giornoDto.getDuplicazioniGiornoDto()) {
						if (giornoDuplicato.getGiorno() == null || giornoDuplicato.getCliente() == null
								|| giornoDuplicato.getOreOrdinarie() == null) {
							msg = "Le ore non sono state inserite nel giorno: " + giornoDuplicato.getGiorno();
							LOGGER.log(Level.ERROR, msg);
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
								if (giornoDuplicato.getGiorno() == null) {
									msg = "Non è stato inserito il numero del giorno";
									LOGGER.log(Level.ERROR, msg);
									return msg;
								}
								if (giornoDuplicato.getCliente() == null) {
									msg = "Il giorno " + giornoDuplicato.getGiorno() + " non conitente il cliente";
									LOGGER.log(Level.ERROR, msg);
									return msg;
								}
								if (giornoDuplicato.getOreOrdinarie() == null) {

									msg = "Il giorno " + giornoDuplicato.getGiorno() + " non contiente le ore lavorate";
									LOGGER.log(Level.ERROR, msg);
									return msg;
								}
							} else {
								if (giornoDuplicato.getCliente() == null || giornoDuplicato.getOreOrdinarie() != null
										|| giornoDuplicato.getFascia1() != null || giornoDuplicato.getFascia2() != null
										|| giornoDuplicato.getFascia3() != null) {
									msg = "Sono state valorizzate delle ore nel giorno " + giornoDuplicato.getGiorno()
											+ " segnato con " + (giornoDto.getFerie() ? "ferie" : "malattia");
									LOGGER.log(Level.ERROR, msg);
									return msg;
								}
							}
						} else {
							if (giornoDuplicato.getCliente() != null) {
								msg = "E' stato valorizzato il cliente in un giorno segnato come "
										+ (giornoDto.getFerie() ? "ferie" : "malattia");
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getOreOrdinarie() != null) {
								msg = "Sono state valorizzate delle ore in un giorno segnato come "
										+ (giornoDto.getFerie() ? "ferie" : "malattia");
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getFascia1() != null) {
								msg = "Sono state valorizzate delle ore di straordinario fascia1 in un giorno segnato come "
										+ " segnato con " + (giornoDto.getFerie() ? "ferie" : "malattia");
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getFascia2() != null) {
								msg = "Sono state valorizzate delle ore di straordinario fascia2 in un giorno segnato come "
										+ " segnato con " + (giornoDto.getFerie() ? "ferie" : "malattia");
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getFascia3() != null) {
								msg = "Sono state valorizzate delle ore di straordinario fascia3 in un giorno segnato come "
										+ " segnato con " + (giornoDto.getFerie() ? "ferie" : "malattia");
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
						}
					}
				} else {
					msg = "Un giorno è stato segnato con piu di uno fra i seguenti: ferie, malattie e permessi";
					LOGGER.log(Level.ERROR, msg);
					return msg;
				}

			} else {
				if (giornoDto.getFerie() != null || giornoDto.getMalattie() != null
						|| giornoDto.getPermessi() != null) {
					msg = "Un giorno è stato segnato con piu di uno fra i seguenti: ferie, malattie e permessi";
					LOGGER.log(Level.ERROR, msg);
					return msg;
				}
			}

			if (contratto != null && contratto.getTipoContratto() != null) {

				if (contratto.getTipoContratto().getId() == 1 || contratto.getTipoContratto().getId() == 2) {
					if (giornoDto.getFerie() != null || giornoDto.getPermessi() != null
							|| giornoDto.getMalattie() != null) {
						msg = "Non sono previsti ferie malattie o permessi per il contratto: "
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
			msg = "Le note non sono state inserte corretteamlete";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		return msg;

	}

	public String validateRapportiniInviati(RapportinoInviato rapportinoInviato) {
		String msg = null;
		if (rapportinoInviato.getId() == null) {

			if (rapportinoInviato.getNome() == null || rapportinoInviato.getNome().equals("")) {
				msg = "Le nome non è stato inserito corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getCognome() == null || rapportinoInviato.getCognome().equals("")) {
				msg = "Il cognome non è stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getCodiceFiscale() == null || rapportinoInviato.getCodiceFiscale().equals("")) {
				msg = "Il codice fiscale non è stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getMese() == null || rapportinoInviato.getAnno() == null) {
				msg = "Il mese non è stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;

			}

		} else {
			msg = "Non è stato possibile effettuare l'inserimento, il campo \"id\" è valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		return msg;

	}

	public String validateFreeze(RapportinoInviato rapportinoInviato) {
		String msg = null;
		if (rapportinoInviato.getId() != null) {

			if (rapportinoInviato.getCheckFreeze() == null) {
				msg = "Non è stato valorizzato il valore \"checkFreeze\"";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

		} else {
			msg = "Non è stato possibile effettuare l'inserimento, il campo \"id\" è valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		return msg;

	}

	public Boolean validateFieldsForPath(RapportinoRequest rapportinoRequest) {

		if (rapportinoRequest != null) {

			if (rapportinoRequest.getCodiceFiscale() == null || rapportinoRequest.getCodiceFiscale().equals("")) {
				LOGGER.log(Level.ERROR, "codice fiscale del rapportinoRequest non è valorizzato");
				return false;
			}

			Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(rapportinoRequest.getCodiceFiscale());

			if (anagrafica == null) {

				LOGGER.log(Level.ERROR, "anagrafica non esistente");

				return false;
			}

			if (rapportinoRequest.getAnno() == null) {
				LOGGER.log(Level.ERROR, "anno request del rapportinoRequest non è valorizzato");
				return false;
			}

			if (rapportinoRequest.getMese() == null) {
				LOGGER.log(Level.ERROR, "mese request del rapportinoRequest non è valorizzato");
				return false;
			}

			return true;

		} else {
			return false;
		}
	}
}
