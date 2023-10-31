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
				msg = " Codice fiscale del rapportinoDto non e' valorizzato";
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
				msg = " Anno del rapportino non e' valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (rapportinoDto.getMeseRequest() == null) {
				msg = " Mese del rapportino non e' valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			return msg;

		} else {
			msg = " Rapportino non e' valorizzato";
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
			double totOre = 0.0;
			double totStraordinario1 = 0.0;
			double totStraordinario2 = 0.0;
			double totStraordinario3 = 0.0;
			double permessi = 0.0;
			boolean ferieOrMalattie = false;
			boolean checkWeekend = (giornoDto.getNomeGiorno().equals("sabato")
					|| giornoDto.getNomeGiorno().equals("domenica"));
			if (giornoDto.getNumeroGiorno() != null) {
				if (giornoDto.getFerie() == null && giornoDto.getMalattie() == null
						&& giornoDto.getPermessi() == null) {

					for (DuplicazioniGiornoDto giornoDuplicato : giornoDto.getDuplicazioniGiornoDto()) {
						if (giornoDuplicato.getOreOrdinarie() != null) {
							if (giornoDuplicato.getCliente() == null) {
								msg = " Il giorno: " + giornoDto.getNumeroGiorno() + " non contiene il cliente";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDto.getNote() == null || giornoDto.getNote().equals("")) {
								msg = " Il giorno: " + giornoDto.getNumeroGiorno() + " non contiente le note";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							totOre += giornoDuplicato.getOreOrdinarie();
							if (giornoDuplicato.getFascia1() != null)
								totStraordinario1 += giornoDuplicato.getFascia1();
							if (giornoDuplicato.getFascia2() != null)
								totStraordinario2 += giornoDuplicato.getFascia2();
							if (giornoDuplicato.getFascia3() != null)
								totStraordinario3 += giornoDuplicato.getFascia3();

						} else {
							if (giornoDuplicato.getCliente() != null) {
								msg = " Nel giorno: " + giornoDto.getNumeroGiorno()
										+ " e' stato inseito un cliente senza le ore";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getFascia1() != null || giornoDuplicato.getFascia2() != null
									|| giornoDuplicato.getFascia3() != null) {
								msg = " Sono state dichiarate delle ore di straordinario nel giorno "
										+ giornoDto.getNumeroGiorno() + " dove non sono state inserite le ore";
							}
							checkWeekend = (giornoDto.getNomeGiorno().equals("sabato")
									|| giornoDto.getNomeGiorno().equals("domenica"));
						}
					}

				} else if ((giornoDto.getFerie() != null && giornoDto.getMalattie() == null
						&& giornoDto.getPermessi() == null)
						|| (giornoDto.getMalattie() != null && giornoDto.getFerie() == null
								&& giornoDto.getPermessi() == null)
						|| (giornoDto.getPermessi() != null && giornoDto.getFerie() == null
								&& giornoDto.getMalattie() == null)) {

					for (DuplicazioniGiornoDto giornoDuplicato : giornoDto.getDuplicazioniGiornoDto()) {

						if (giornoDto.getPermessi() != null) {
							if (giornoDuplicato.getCliente() == null) {
								msg = " Il giorno " + giornoDto.getNumeroGiorno() + " non conitente il cliente";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getOreOrdinarie() == null) {
								msg = " Il giorno " + giornoDto.getNumeroGiorno() + " non contiente le ore lavorate";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDto.getNote() == null || giornoDto.getNote().equals("")) {
								msg = " Il giorno: " + giornoDto.getNumeroGiorno() + " non contiente le note";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							totOre += giornoDuplicato.getOreOrdinarie();
							if (giornoDuplicato.getFascia1() != null)
								totStraordinario1 += giornoDuplicato.getFascia1();
							if (giornoDuplicato.getFascia2() != null)
								totStraordinario2 += giornoDuplicato.getFascia2();
							if (giornoDuplicato.getFascia3() != null)
								totStraordinario3 += giornoDuplicato.getFascia3();
							if (giornoDto.getPermessi() != null)
								permessi = giornoDto.getPermessi();
						} else {
							if (giornoDuplicato.getCliente() != null) {
								msg = " Nel giorno " + giornoDto.getNumeroGiorno() + " e' stato inserito il cliente";
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							if (giornoDuplicato.getOreOrdinarie() != null || giornoDuplicato.getFascia1() != null
									|| giornoDuplicato.getFascia2() != null || giornoDuplicato.getFascia3() != null) {
								msg = " Sono state dichiarate delle ore lavorate nel giorno "
										+ giornoDto.getNumeroGiorno() + " segnato con "
										+ (giornoDto.getFerie() != null ? "ferie" : "malattia");
								LOGGER.log(Level.ERROR, msg);
								return msg;
							}
							ferieOrMalattie = true;
						}
					}
				} else {
					if (giornoDto.getNumeroGiorno() != null) {
						msg = " Il giorno " + giornoDto.getNumeroGiorno() + " e' stato segnato con piu di uno fra i"
								+ "seguenti: ferie, malattie e permessi";
					} else {
						msg = " Un giorno e' stato segnato con piu di uno fra i seguenti: ferie, malattie e permessi";
					}
					LOGGER.log(Level.ERROR, msg);
					return msg;
				}
			} else {
				msg = " Il numero di un giorno non e' stato valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (contratto != null && contratto.getTipoContratto() != null) {

				if (contratto.getTipoContratto().getId() == 1 || contratto.getTipoContratto().getId() == 2) {
					if (giornoDto.getFerie() != null || giornoDto.getPermessi() != null
							|| giornoDto.getMalattie() != null) {
						msg = " Non sono previsti ferie malattie o permessi per un contratto: "
								+ contratto.getTipoContratto().getDescrizione();
						LOGGER.log(Level.ERROR, msg);
						return msg;
					}
				}
			}
			if (!ferieOrMalattie) {
				if (checkWeekend) {
					if (totOre + permessi > 8) {
						msg = " Nel giorno: " + giornoDto.getNumeroGiorno()
								+ " sono state dichiarate piu' di otto ore ordinarie,"
								+ " se intendevi inserire straordianri utilizza i campi appositi";
					}
				} else {
					if (totOre + permessi != 8) {
						if (totOre + permessi > 8) {
							msg = " Nel giorno: " + giornoDto.getNumeroGiorno()
									+ " sono state dichiarate piu' di otto ore fra permessi e ore ordinarie,"
									+ " se intendevi inserire straordianri utilizza i campi appositi";
						}
						if (totOre + permessi < 8) {
							msg = " Nel giorno: " + giornoDto.getNumeroGiorno()
									+ " le ore fra permessi e ore ordinarie sono inferiori a 8"
									+ " ricontrolla i dati inseriti";
						}
						LOGGER.log(Level.ERROR, msg);
						return msg;
					}
				}
				if (totStraordinario1 > 2) {
					msg = " Nel giorno: " + giornoDto.getNumeroGiorno()
							+ " le ore di straoridinario 18-20 superano le 2 ore";
					LOGGER.log(Level.ERROR, msg);
					return msg;
				}
				if (totStraordinario2 > 2) {
					msg = " Nel giorno: " + giornoDto.getNumeroGiorno()
							+ " le ore di straoridinario 20-22 superano le 2 ore";
					LOGGER.log(Level.ERROR, msg);
					return msg;
				}
				if (totStraordinario3 > 13) {
					msg = " Nel giorno: " + giornoDto.getNumeroGiorno()
							+ " le ore di straoridinario 22-09 superano le 13 ore";
					LOGGER.log(Level.ERROR, msg);
					return msg;
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
				msg = " Le nome non e' stato inserito corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getCognome() == null || rapportinoInviato.getCognome().equals("")) {
				msg = " Il cognome non e' stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getCodiceFiscale() == null || rapportinoInviato.getCodiceFiscale().equals("")) {
				msg = " Il codice fiscale non e' stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
			if (rapportinoInviato.getMese() == null || rapportinoInviato.getAnno() == null) {
				msg = " Il mese non e' stato inserito correttamete corretteamlete";
				LOGGER.log(Level.ERROR, msg);
				return msg;

			}

		} else {
			msg = " Non e' stato possibile effettuare l'inserimento, il campo \"id\" e' valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		return msg;

	}

	public String validateFreeze(RapportinoInviato rapportinoInviato) {
		String msg = null;
		if (rapportinoInviato.getId() != null) {

			if (rapportinoInviato.getCheckFreeze() == null) {
				msg = " Non e' stato valorizzato il valore \"checkFreeze\"";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

		} else {
			msg = " Non e' stato possibile effettuare l'inserimento, il campo \"id\" non e' valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		return msg;

	}

	public String validateFieldsForPath(RapportinoRequest rapportinoRequest) {
		String msg = null;
		if (rapportinoRequest != null) {

			if (rapportinoRequest.getCodiceFiscale() == null || rapportinoRequest.getCodiceFiscale().equals("")) {
				msg = " Codice fiscale del rapportinoRequest non e' valorizzato";
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
				msg = " Anno del rapportino non e' valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

			if (rapportinoRequest.getMese() == null) {
				msg = " Mese del rapportino non e' valorizzato";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}

		} else {
			msg = " Rapportino non e' valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}
		return msg;
	}

}
