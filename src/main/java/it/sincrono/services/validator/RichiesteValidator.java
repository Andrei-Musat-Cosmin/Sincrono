package it.sincrono.services.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.TipoRichieste;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.RichiestaRepository;
import it.sincrono.repositories.TipoRichiestaRepository;
import it.sincrono.repositories.dto.DuplicazioniRichiestaDto;
import it.sincrono.repositories.dto.RichiestaDto;
import it.sincrono.services.utils.ConvertInDto;

@Component
public class RichiesteValidator {
	private static final Logger LOGGER = LogManager.getLogger(RichiesteValidator.class);

	@Autowired
	TipoRichiestaRepository tipoRichiestaRepository;

	@Autowired
	AnagraficaRepository anagraficaRepository;

	@Autowired
	ConvertInDto convertInDto;

	public String validateInsert(RichiestaDto richiestaDto) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

		String msg = null;

		if (richiestaDto == null || richiestaDto.getId() != null) {
			msg = " Id della richiestaDto non deve essere valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		if (richiestaDto.getMese() == null) {
			msg = " il mese della richiesta deve essere valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		if (richiestaDto.getAnno() == null) {
			msg = " l'anno della richiesta deve essere valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		String codiceFiscale = richiestaDto.getCodiceFiscale();
		if (codiceFiscale == null || codiceFiscale.isEmpty()) {

			msg = " il codice fiscale della richiesta deve essere valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		if (richiestaDto.getList().stream().filter(elem -> (elem.getPermessi() != null && elem.getPermessi() == true))
				.count() > 1) {

			msg = " puoi in inserire solo un permesso per volta";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		if (!(richiestaDto.getList().stream().map(DuplicazioniRichiestaDto::getnGiorno).distinct()
				.count() == richiestaDto.getList().size())) {

			msg = " in una richiesta di ferie ci può essere solo un giorno univoco per mese";
			LOGGER.log(Level.ERROR, msg);
			return msg;
		}

		LocalDate dataDaControllare = LocalDate.of(richiestaDto.getAnno(), richiestaDto.getMese(),
				richiestaDto.getList().get(0).getnGiorno());

		if (richiestaDto.getList().get(0).getPermessi() != null
				&& richiestaDto.getList().get(0).getPermessi() == true) {

			if (dataDaControllare.isBefore(LocalDate.now())) {
				msg = " per i permessi la data deve essere o uguale o maggiore della data odierna";
				LOGGER.log(Level.ERROR, msg);
				return msg;

			}

		}

		if (richiestaDto.getList().get(0).getFerie() != null && richiestaDto.getList().get(0).getFerie() == true) {

			if (dataDaControllare.isBefore(LocalDate.now()) && !dataDaControllare.isEqual(LocalDate.now())) {
				msg = " per le ferie la data deve essere maggiore della data odierna";
				LOGGER.log(Level.ERROR, msg);
				return msg;

			}

		}

		for (DuplicazioniRichiestaDto duplicazioniRichiestaDto : richiestaDto.getList()) {
			if ((duplicazioniRichiestaDto.getFerie() != null || duplicazioniRichiestaDto.getPermessi() != null)
					|| (duplicazioniRichiestaDto.getFerie() == null
							&& duplicazioniRichiestaDto.getPermessi() == null)) {
				if (duplicazioniRichiestaDto.getnGiorno() == null) {

					msg = " nella richiesta se ferie o permessi sono valorizzati, nGiorno deve essere valorizzato";
					LOGGER.log(Level.ERROR, msg);
					return msg;

				} else {

					if (!(duplicazioniRichiestaDto.getnGiorno() > 0 && duplicazioniRichiestaDto.getnGiorno() <= 31)) {

						msg = " nella richiesta il numero giorno e fuori dal range";
						LOGGER.log(Level.ERROR, msg);
						return msg;
					}

				}

				if (duplicazioniRichiestaDto.getPermessi() != null) {
					String daOra = duplicazioniRichiestaDto.getDaOra();
					String aOra = duplicazioniRichiestaDto.getaOra();
					if (daOra == null || aOra == null || daOra.isEmpty() || aOra.isEmpty()) {
						msg = " nella richiesta se permessi sono valorizzati, daOra e aOra devono essere valorizzati";
						LOGGER.log(Level.ERROR, msg);
						return msg;
					} else {

						LocalTime time1 = LocalTime.parse(daOra, formatter);
						LocalTime time2 = LocalTime.parse(aOra, formatter);

						if (time2.isBefore(time1)) {

							msg = " nella richiesta daOra deve essere minore di aOra";
							LOGGER.log(Level.ERROR, msg);
							return msg;

						}

					}

				}
			} else {
				msg = " nella richiesta deve essere valorizzato o ferie o permessi";
				LOGGER.log(Level.ERROR, msg);
				return msg;
			}
		}

		if (isExist(richiestaDto)) {

			msg = " richiesta già esistente";
			LOGGER.log(Level.ERROR, msg);
			return msg;

		}

		return msg;

	}

	public Boolean validateListRichieste(RichiestaDto richiestaDto) {

		if (richiestaDto.getMese() == null) {
			LOGGER.log(Level.ERROR, "il mese della richiesta deve essere valorizzato");
			return false;
		}

		if (richiestaDto.getAnno() == null) {
			LOGGER.log(Level.ERROR, "l'anno della richiesta deve essere valorizzato");
			return false;
		}

		String codiceFiscale = richiestaDto.getCodiceFiscale();
		if (codiceFiscale == null || codiceFiscale.isEmpty()) {
			LOGGER.log(Level.ERROR, "il codice fiscale della richiesta deve essere valorizzato");
			return false;
		}

		return true;

	}

	public Boolean validateCambiaStato(RichiestaDto richiestaDto) {
		if (richiestaDto.getId() == null) {
			LOGGER.log(Level.ERROR, "l'id della richiesta deve essere valorizzato");
			return false;
		}

		if (richiestaDto.getStato() == null) {
			LOGGER.log(Level.ERROR, "lo stato della richiesta deve essere valorizzato");
			return false;
		}

		/*
		 * if (richiestaDto.getStato() == true && richiestaDto.getNote()!=null) {
		 * LOGGER.log(Level.ERROR,
		 * "quando lo stato della richiesta è positivo le note non devono essere valorizzate"
		 * ); return false; }
		 */

		if (richiestaDto.getAnno() == null) {
			LOGGER.log(Level.ERROR, "l'anno della richiesta deve essere valorizzato");
			return false;
		}

		if (richiestaDto.getMese() == null) {
			LOGGER.log(Level.ERROR, "il mese della richiesta deve essere valorizzato");
			return false;
		}

		if (richiestaDto.getCodiceFiscale() == null) {
			LOGGER.log(Level.ERROR, "il codice fiscale della richiesta deve essere valorizzato");
			return false;
		}

		Anagrafica anagrafica = anagraficaRepository.findByCodiceFiscale(richiestaDto.getCodiceFiscale());

		if (anagrafica == null) {
			LOGGER.log(Level.ERROR, "il codice fiscale deve appartenere a una anagrafica esistente");
			return false;

		}

		/*
		 * if (richiestaDto.getStato()==false && richiestaDto.getNote() == null) {
		 * LOGGER.log(Level.ERROR,
		 * "le note della richiesta devono essere valorizzate nel rifiuto"); return
		 * false; }
		 */

		/*
		 * if (richiestaDto.getStato() == true) { LOGGER.log(Level.ERROR,
		 * "Note disattivate"); richiestaDto.setNote("false"); }
		 */

		return true;

	}

	// diventa controllo intersecazione
	/*
	 * public Boolean isExist(RichiestaDto richiestaDto) {
	 * 
	 * List<RichiestaDto> listRichiestaDto = null;
	 * 
	 * Anagrafica anagrafica =
	 * anagraficaRepository.findByCodiceFiscale(richiestaDto.getCodiceFiscale());
	 * 
	 * List<TipoRichieste> tipoRichieste =
	 * tipoRichiestaRepository.getRichieste(richiestaDto.getAnno(),
	 * richiestaDto.getMese(), anagrafica.getId());
	 * 
	 * if (tipoRichieste != null && tipoRichieste.size() > 0) listRichiestaDto =
	 * convertInDto.convertInDifferentRichiestaDto(tipoRichieste);
	 * 
	 * return tipoRichieste == null || tipoRichieste.size() == 0 ? false :
	 * listRichiestaDto.stream().filter(elem ->
	 * elem.equals(richiestaDto)).collect(Collectors.toList()) .size() > 0 ? true :
	 * false;
	 * 
	 * }
	 */

	public Boolean isExist(RichiestaDto richiestaDto) {

		return richiestaDto.getList().get(0).getPermessi() != null
				&& richiestaDto.getList().get(0).getPermessi() == true ? validateExistPermesso(richiestaDto)
						: validateExistferie(richiestaDto);

	}

	public Boolean validateExistPermesso(RichiestaDto richiestaDto) {

		List<TipoRichieste> tipoRichiesteList = tipoRichiestaRepository.getRichiesteValidate(richiestaDto.getAnno(),
				richiestaDto.getMese(),
				anagraficaRepository.findByCodiceFiscale(richiestaDto.getCodiceFiscale()).getId(),
				richiestaDto.getList().get(0).getnGiorno(), richiestaDto.getList().get(0).getaOra(),
				richiestaDto.getList().get(0).getDaOra());

		return tipoRichiesteList == null || tipoRichiesteList.size()==0 ? false : true;

	}

	public Boolean validateExistferie(RichiestaDto richiestaDto) {

		Boolean checkExistFerie = false;

		List<TipoRichieste> tipoRichiesteList = tipoRichiestaRepository.getRichieste(richiestaDto.getAnno(),
				richiestaDto.getMese(),
				anagraficaRepository.findByCodiceFiscale(richiestaDto.getCodiceFiscale()).getId());

		for (DuplicazioniRichiestaDto duplicazioniRichiesteDto : richiestaDto.getList()) {

			for (TipoRichieste tipoRichieste : tipoRichiesteList) {

				if (duplicazioniRichiesteDto.getnGiorno() == tipoRichieste.getnGiorno()) {

					checkExistFerie = true;

				}

			}

		}

		return checkExistFerie;

	}

	public String validateUpdate(RichiestaDto richiestaDto) {

		String msg = null;

		if (richiestaDto == null || richiestaDto.getId() != null) {

			msg = " Id della richiestaDto deve essere valorizzato";
			LOGGER.log(Level.ERROR, msg);
			return msg;

		}

		return msg;

	}

}
